package com.jl.framework.ui.widget.stickyheadersrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;

/*
 *  Copyright 2016 https://github.com/jacobtabak
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *    from https://github.com/timehop/sticky-headers-recyclerview
 */

public class StickyRecyclerHeadersTouchListener implements RecyclerView.OnItemTouchListener {
  private final GestureDetector mTapDetector;
  private final RecyclerView mRecyclerView;
  private final StickyRecyclerHeadersDecoration mDecor;
  private OnHeaderClickListener mOnHeaderClickListener;

  public interface OnHeaderClickListener {
    void onHeaderClick(View header, int position, long headerId);
  }

  public StickyRecyclerHeadersTouchListener(final RecyclerView recyclerView,
                                            final StickyRecyclerHeadersDecoration decor) {
    mTapDetector = new GestureDetector(recyclerView.getContext(), new SingleTapDetector());
    mRecyclerView = recyclerView;
    mDecor = decor;
  }

  public StickyRecyclerHeadersAdapter getAdapter() {
    if (mRecyclerView.getAdapter() instanceof StickyRecyclerHeadersAdapter) {
      return (StickyRecyclerHeadersAdapter) mRecyclerView.getAdapter();
    } else {
      throw new IllegalStateException("A RecyclerView with " +
          StickyRecyclerHeadersTouchListener.class.getSimpleName() +
          " requires a " + StickyRecyclerHeadersAdapter.class.getSimpleName());
    }
  }


  public void setOnHeaderClickListener(OnHeaderClickListener listener) {
    mOnHeaderClickListener = listener;
  }

  @Override
  public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
    if (this.mOnHeaderClickListener != null) {
      boolean tapDetectorResponse = this.mTapDetector.onTouchEvent(e);
      if (tapDetectorResponse) {
        // Don't return false if a single tap is detected
        return true;
      }
      if (e.getAction() == MotionEvent.ACTION_DOWN) {
        int position = mDecor.findHeaderPositionUnder((int)e.getX(), (int)e.getY());
        return position != -1;
      }
    }
    return false;
  }

  @Override
  public void onTouchEvent(RecyclerView view, MotionEvent e) { /* do nothing? */ }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    // do nothing
  }

  private class SingleTapDetector extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
      int position = mDecor.findHeaderPositionUnder((int) e.getX(), (int) e.getY());
      if (position != -1) {
        View headerView = mDecor.getHeaderView(mRecyclerView, position);
        long headerId = getAdapter().getHeaderId(position);
        mOnHeaderClickListener.onHeaderClick(headerView, position, headerId);
        mRecyclerView.playSoundEffect(SoundEffectConstants.CLICK);
        headerView.onTouchEvent(e);
        return true;
      }
      return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
      return true;
    }
  }
}
