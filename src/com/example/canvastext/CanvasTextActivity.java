package com.example.canvastext;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CanvasTextActivity extends Activity {

	private View canvasView;
	private Button switchButton;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_text);
        canvasView = findViewById(R.id.canvasView);
        switchButton = (Button) findViewById(R.id.button);
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.HONEYCOMB){
        	switchButton.setEnabled(false);
        	switchButton.setText(R.string.not_available);

        }else{
        	switchButton.setText(R.string.enabled);

        }

    }

    @TargetApi(11)
	public void switchHW(View view){
    	int currentType = canvasView.getLayerType();
    	
    	if (currentType == View.LAYER_TYPE_NONE) {
    		canvasView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    		switchButton.setText(R.string.disabled);

    	}else{
    		canvasView.setLayerType(View.LAYER_TYPE_NONE,null);
    		switchButton.setText(R.string.enabled);

    	}
    	
    }
}
