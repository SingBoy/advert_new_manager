package cn.net.ibingo.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.RedisGenerator;
import cn.net.ibingo.core.model.FristChannel;

import com.alibaba.fastjson.JSON;

/**
 * 一级渠道缓存
 * @author YuanLian
 *
 */
@Repository
public class FristChannelRedis extends RedisGenerator<String,String> {

	
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	
	/**
	 * 添加FristChannel对象
	 * @param product
	 * @return
	 */
	public boolean add(final FristChannel fristChannel) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) 
					throws DataAccessException{
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key  = serializer.serialize(ConstantConfig.PROJECT_NAME + "-fristChannel_"+ fristChannel.getCode());
				byte[] value = serializer.serialize(JSON.toJSONString(fristChannel));
				return connection.setNX(key,value);
			}
		});
		return result;
	}
	
	
	/**
	 * 根据项目名+key删除对象
	 * @param key
	 */
	public void delete(String key) {
		redisTemplate.delete(ConstantConfig.PROJECT_NAME + "-fristChannel_"+ key);
	}
	
}
