package com.dev_hieu.studentmatch.camera;


import android.hardware.Camera;

import com.google.android.gms.vision.CameraSource;

import java.lang.reflect.Field;

public class CameraVisionManager {

    public static boolean cameraFocus(CameraSource cameraSource, String focusMode) {
        Field[] declaredFields = CameraSource.class.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getType() == Camera.class) {
                field.setAccessible(true);
                try {
                    Camera camera = (Camera) field.get(cameraSource);

                    if (camera != null) {
                        Camera.Parameters parameters = camera.getParameters();

                        if (!parameters.getSupportedFocusModes().contains(focusMode)) {
                            return false;
                        }

                        parameters.setFocusMode(focusMode);
                        camera.setParameters(parameters);
                        return true;
                    }

                    return false;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                break;
            }
        }

        return false;
    }
}
