package nl.frankkie.smswidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by frankb on 19/06/2017.
 */

public class MyAppWidgetConfigureActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doStuff();
    }

    public void doStuff() {
        final int mAppWidgetId;
        Context context = this;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        } else {
            //no widget.
            Toast.makeText(this, "Geen widget geconfigureerd.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        //Create canceled result, for if user backs out
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_CANCELED, resultValue);
        //
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.appwidget);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);

        //Config UI
        setContentView(R.layout.activity_configure);
        findViewById(R.id.appwidget_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(mAppWidgetId);
            }
        });
    }

    public void done(int mAppWidgetId) {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}
