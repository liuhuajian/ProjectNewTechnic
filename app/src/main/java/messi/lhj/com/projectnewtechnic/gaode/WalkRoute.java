package messi.lhj.com.projectnewtechnic.gaode;

import android.content.Context;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.RouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;

import java.util.List;

import messi.lhj.com.projectnewtechnic.util.Logger;

/**
 * Created by messi on 2017/9/2.
 */

public class WalkRoute implements RouteSearch.OnRouteSearchListener {

    private static final int ROUTE_TYPE_WALK = 1;
    private LatLonPoint mStartPoint;
    private LatLonPoint mEndPoint;
    private RouteSearch mRouteSearch;
    private Context context;

    private RouteOverlay routeOverlay;
    public WalkRouteOverlay walkRouteOverlay;
    private AMap aMap;

    public WalkRoute(Context context , AMap aMap){
        this.context = context;
        this.aMap = aMap;
        walkRouteSearch();
    }

    private void walkRouteSearch(){
        mStartPoint = new LatLonPoint(31.138162, 121.417953);
        mEndPoint = new LatLonPoint(31.152905,121.442732);
        mRouteSearch = new RouteSearch(context);
        mRouteSearch.setRouteSearchListener(this);
        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
    }

    private void searchRouteResult(int routeType, int mode) {
        try {

            if (mStartPoint == null) {

                Toast.makeText(context, "定位中，稍后再试...",Toast.LENGTH_SHORT).show();
                return;
            }
            if (mEndPoint == null) {

                Toast.makeText(context, "终点未设置",Toast.LENGTH_SHORT).show();
            }
            final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                    mStartPoint, mEndPoint);
            if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
                RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
                mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
            }


        } catch (Exception e) {
           Toast.makeText(context,"1Exception: " + e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
        Logger.d("onWalkRouteSearched-->"+i);
        if (i ==1000){
            //在地图上绘制路径：
            List<WalkPath> paths = walkRouteResult.getPaths();

            final WalkPath walkPath = walkRouteResult.getPaths()
                    .get(0);
            if (walkRouteOverlay != null){
                walkRouteOverlay.removeFromMap();
            }
            walkRouteOverlay = new WalkRouteOverlay(
                    context, aMap, walkPath,
                    walkRouteResult.getStartPos(),
                    walkRouteResult.getTargetPos());
            walkRouteOverlay.addToMap();
            walkRouteOverlay.zoomToSpan();
        }
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
