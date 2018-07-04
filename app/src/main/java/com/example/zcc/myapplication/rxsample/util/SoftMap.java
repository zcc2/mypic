package com.example.zcc.myapplication.rxsample.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftMap<K,V> {
	
	private SoftReference<HashMap<K, V>> innerMap = new SoftReference<HashMap<K,V>>(null);
	
	public V get(K k){
		final HashMap<K, V> map = innerMap.get();
		if(map != null){
			return map.get(k);
		}
		return null;
	}
	
	public void put(K k, V v){
		HashMap<K, V> map = innerMap.get();
		if(map == null){
			map = new HashMap<K, V>();
			map.put(k, v);
			innerMap = new SoftReference<HashMap<K,V>>(map);
		}else{
			map.put(k, v);
		}
	}
	
	public boolean containsKey(K k){
		HashMap<K, V> map = innerMap.get();
		if(map != null){
			return map.containsKey(k);
		}
		return false;
	}
}
