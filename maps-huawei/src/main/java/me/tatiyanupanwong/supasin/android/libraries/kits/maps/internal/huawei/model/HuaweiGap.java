/*
 * Copyright (C) 2020 Supasin Tatiyanupanwong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.tatiyanupanwong.supasin.android.libraries.kits.maps.internal.huawei.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.Gap;

class HuaweiGap extends HuaweiPatternItem implements Gap {

    private final com.huawei.hms.maps.model.Gap mDelegate;

    private HuaweiGap(com.huawei.hms.maps.model.Gap delegate) {
        mDelegate = delegate;
    }

    HuaweiGap(float length) {
        this(new com.huawei.hms.maps.model.Gap(length));
    }

    @Override
    public float getLength() {
        return mDelegate.length;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HuaweiGap that = (HuaweiGap) obj;

        return mDelegate.equals(that.mDelegate);
    }

    @Override
    public int hashCode() {
        return mDelegate.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return mDelegate.toString();
    }


    static Gap wrap(com.huawei.hms.maps.model.Gap delegate) {
        return new HuaweiGap(delegate);
    }

    static com.huawei.hms.maps.model.Gap unwrap(Gap wrapped) {
        return ((HuaweiGap) wrapped).mDelegate;
    }

}
