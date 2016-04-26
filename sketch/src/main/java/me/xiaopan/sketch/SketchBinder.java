/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.sketch;

import java.lang.ref.WeakReference;

/**
 * Request与ImageView的关系绑定器
 */
public class SketchBinder {
    private DisplayRequest displayRequest;
    private WeakReference<ImageViewInterface> imageViewReference;

    public SketchBinder(ImageViewInterface imageView) {
        this.imageViewReference = new WeakReference<ImageViewInterface>(imageView);
    }

    public void setDisplayRequest(DisplayRequest displayRequest) {
        this.displayRequest = displayRequest;
    }

    public ImageViewInterface getImageViewInterface() {
        final ImageViewInterface imageViewInterface = imageViewReference.get();
        if (displayRequest != null) {
            DisplayRequest holderDisplayRequest = BindFixedRecycleBitmapDrawable.findDisplayRequest(imageViewInterface);
            if (holderDisplayRequest != null && holderDisplayRequest == displayRequest) {
                return imageViewInterface;
            } else {
                return null;
            }
        } else {
            return imageViewInterface;
        }
    }

    public boolean isBroken() {
        return getImageViewInterface() == null;
    }
}
