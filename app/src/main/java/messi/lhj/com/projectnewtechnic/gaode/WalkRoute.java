package messi.lhj.com.projectnewtechnic.gaode;

import android.content.Context;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;

import java.util.List;

import messi.lhj.com.projectnewtechnic.util.Logger;

/**
 * Created by messi on 2017/9/2.
 */

public class WalkRoute {

    private static final int ROUTE_TYPE_WALK = 1;
    private LatLonPoint mStartPoint;
    private LatLonPoint mEndPoint;
    private RouteSearch mRouteSearch;
    private Context context;

    public WalkRouteOverlay walkRouteOverlay;
    private AMap aMap;
    private LatLng startLatlng;
    private LatLng endLatlng;

    public WalkRoute(Context context, AMap aMap) {
        this.context = context;
        this.aMap = aMap;
    }

    public WalkRoute setStartLocation(LatLng startLatlng){
        this.startLatlng = startLatlng;
        return this;
    }
    public WalkRoute setEndLatlng(LatLng endLatlng){
        this.endLatlng = endLatlng;
        return this;
    }

    public void handleSearch(){
        walkRouteSearch();
    }

    private void walkRouteSearch() {
        mStartPoint = new LatLonPoint(startLatlng.latitude, startLatlng.longitude);
        mEndPoint = new LatLonPoint(endLatlng.latitude, endLatlng.longitude);
        Logger.d(mStartPoint.toString() + "--->" + mEndPoint.toString());
        mRouteSearch = new RouteSearch(context);
        mRouteSearch.setRouteSearchListener(myRouteSearchListener);
        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
    }

    private void searchRouteResult(int routeType, int mode) {
        try {

            if (mStartPoint == null) {

                Toast.makeText(context, "定位中，稍后再试...", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mEndPoint == null) {

                Toast.makeText(context, "终点未设置", Toast.LENGTH_SHORT).show();
            }
            final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                    mStartPoint, mEndPoint);
            if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
                RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
                mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
            }


        } catch (Exception e) {
            Toast.makeText(context, "1Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    MyRouteSearchListener myRouteSearchListener = new MyRouteSearchListener() {
        @Override
        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
            super.onWalkRouteSearched(walkRouteResult, i);
            Logger.d("onWalkRouteSearched-->" + i);
            if (i == 1000) {
                //在地图上绘制路径：
                final WalkPath walkPath = walkRouteResult.getPaths()
                        .get(0);
                if (walkRouteOverlay != null){
                    walkRouteOverlay.removeFromMap();
                }
                if (walkRouteOverlay != null) {
                    List<Polyline> allPolyLines = walkRouteOverlay.allPolyLines;
                    Logger.d(allPolyLines.toString());
                    for (Polyline polyline : allPolyLines) {
                        polyline.remove();
                    }
                }
                walkRouteOverlay = new WalkRouteOverlay(
                        context, aMap, walkPath,
                        walkRouteResult.getStartPos(),
                        walkRouteResult.getTargetPos());
                walkRouteOverlay.addToMap();
                walkRouteOverlay.zoomToSpan();
                float distanceFromStartToEnd = walkRouteOverlay.getDistanceFromStartToEnd();
                Logger.d("distanceFromStartToEnd-->"+distanceFromStartToEnd);
            }
        }
    };

}
