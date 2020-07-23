/*
 * Copyright (C) 2020 Supasin Tatiyanupanwong
 * Copyright (C) 2017 Google Inc.
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

package me.tatiyanupanwong.supasin.android.libraries.kits.maps.utils.collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.MapClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.Polygon;

/**
 * Keeps track of collections of polygons on the map. Delegates all {@link Polygon}-related events
 * to each collection's individually managed listeners.
 * <p/>
 * All polygon operations (adds and removes) should occur via its {@linkplain Collection collection}
 * class. That is, don't add a polygon via a collection, then remove it via {@link Polygon#remove()}
 */
public class PolygonManager extends
        MapObjectManager<Polygon, PolygonManager.Collection> implements
        MapClient.OnPolygonClickListener {

    public PolygonManager(MapClient map) {
        super(map);
    }

    @Override
    void setListenersOnUiThread() {
        if (mMap != null) {
            mMap.setOnPolygonClickListener(this);
        }
    }

    @Override
    public Collection newCollection() {
        return new Collection();
    }

    @Override
    protected void removeObjectFromMap(@NonNull Polygon object) {
        object.remove();
    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {
        Collection collection = mAllObjects.get(polygon);
        if (collection != null && collection.mPolygonClickListener != null) {
            collection.mPolygonClickListener.onPolygonClick(polygon);
        }
    }


    public class Collection extends MapObjectManager.Collection {
        private MapClient.OnPolygonClickListener mPolygonClickListener;

        public Collection() {}

        public Polygon addPolygon(Polygon.Options opts) {
            Polygon polygon = mMap.addPolygon(opts);
            super.add(polygon);
            return polygon;
        }

        public void addAll(java.util.Collection<Polygon.Options> opts) {
            for (Polygon.Options opt : opts) {
                addPolygon(opt);
            }
        }

        public void addAll(java.util.Collection<Polygon.Options> opts, boolean defaultVisible) {
            for (Polygon.Options opt : opts) {
                addPolygon(opt).setVisible(defaultVisible);
            }
        }

        public void showAll() {
            for (Polygon polygon : getPolygons()) {
                polygon.setVisible(true);
            }
        }

        public void hideAll() {
            for (Polygon polygon : getPolygons()) {
                polygon.setVisible(false);
            }
        }

        public boolean remove(Polygon polygon) {
            return super.remove(polygon);
        }

        public java.util.Collection<Polygon> getPolygons() {
            return getObjects();
        }

        public void setOnPolygonClickListener(
                @Nullable MapClient.OnPolygonClickListener polygonClickListener) {
            mPolygonClickListener = polygonClickListener;
        }
    }

}
