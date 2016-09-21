package com.framework.magicarena.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>Wrap {@link RecyclerView} with {@link PullToRefreshBase} to implement the function of refreshing.</p>
 *
 * <b>Maintenance History</b>:
 * <table>
 * 		<tr>
 * 			<th>Date</th>
 * 			<th>Developer</th>
 * 			<th>Target</th>
 * 			<th>Content</th>
 * 		</tr>
 * 		<tr>
 * 			<td>2016-8-17</td>
 * 			<td>Guo QingJun</td>
 * 			<td>All</td>
 *			<td>Created.</td>
 * 		</tr>
 * </table>
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    /**
     * 获取滑动滚动的方向
     *
     * @return
     */
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    /**
     *
     * @param context Context to create view with
     * @param attrs AttributeSet from wrapped class. Means that anything you
     *            include in the XML layout declaration will be routed to the
     *            created View
     * @return
     */
    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(R.id.recyclerview);
        return recyclerView;
    }

    /**
     * 是否准备好下拉（从左向右拉的刷新）
     *
     * @return true 准备好了 false 不进入刷新状态，没准备好
     */
    @Override
    protected boolean isReadyForPullStart() {
        //获取刷新的View
        RecyclerView refreshableView = this.getRefreshableView();

        if (refreshableView.getChildCount() <= 0) {
            return false;
        }

        //获取RecyclerView中的第一项
        View firstChild = refreshableView.getChildAt(0);
        //获取RecyclerView的顶部内边距
        int paddingTop = refreshableView.getPaddingTop();
        //获取child的顶部外边距
        MarginLayoutParams layoutParams = (MarginLayoutParams) firstChild.getLayoutParams();
        int marginTop = layoutParams.topMargin;
        int firstChildTop = firstChild.getTop();

        return paddingTop + marginTop == firstChildTop;
    }

    /**
     * 是否准备好 上拉加载（从右向左的刷新） 从结束的地方刷新
     *
     * @return true 准备好了 false 没准备好
     */
    @Override
    protected boolean isReadyForPullEnd() {
        //获取刷新的View
        RecyclerView refreshableView = this.getRefreshableView();
        int childCount = refreshableView.getChildCount();

        if (childCount <= 0) {
            return false;
        }

        View child = refreshableView.getChildAt(childCount - 1);
        //获取刷新View的高度
        int refreshViewHeight = refreshableView.getHeight();
        int paddingBottom = refreshableView.getPaddingBottom();
        MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
        //获取RecyclerView底部外边距
        int marginBottom = layoutParams.bottomMargin;
        //获取最后一个item的底部外边距
        int childBottom = child.getBottom();
        //添加一个判断条件，获取一下RecylerView的Adapter中的item条数
        int itemCount = refreshableView.getAdapter().getItemCount();
        //计算最后一个view在适配器中的位置
        int childAdapterPosition = refreshableView.getChildAdapterPosition(child);

        return itemCount == childAdapterPosition + 1 ? paddingBottom + marginBottom + childBottom == refreshViewHeight : false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
    }
}
