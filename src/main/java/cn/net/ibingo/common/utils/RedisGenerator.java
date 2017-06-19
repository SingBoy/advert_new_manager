package cn.net.ibingo.common.utils;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 
 * @author YuanLian
 *
 */
public abstract class RedisGenerator<K extends Serializable, V extends Serializable> {

	@Autowired
	RedisTemplate<K,V> redisTemplate;

	/**
	 * 设置redisTemplate
	 * @param redisTemplate
	 */
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	/**
	 * 获取 RedisSerializer
	 * @return
	 */
	public RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}
	
	
}
