package nl.frankkie.smswidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by frankb on 19/06/2017.
 */

public class MyAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i = 0; i < appWidgetIds.length; i++) {
            Intent intent = new Intent(context, MyBroadcastReceiver.class);
            intent.putExtra("appwidgetid",appWidgetIds[i]);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
            views.setOnClickPendingIntent(R.id.appwidget_button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
        }
    }
}
