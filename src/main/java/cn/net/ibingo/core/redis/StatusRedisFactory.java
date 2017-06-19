package cn.net.ibingo.core.redis;


import cn.net.ibingo.common.utils.RedisGenerator;
import cn.net.ibingo.core.redis.model.StatusRedisModel;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class StatusRedisFactory extends RedisGenerator<String,String> {


	@Autowired
	RedisTemplate<String,String> redisTemplate;

	/**
	 * 添加FristChannel对象
	 * @param
	 * @return
	 */
	public boolean add(final StatusRedisModel statusRedisModel) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key  = serializer.serialize(statusRedisModel.getKey());
				byte[] value = serializer.serialize(JSON.toJSONString(statusRedisModel.getStatusSet()));
				return connection.setNX(key,value);
			}
		});
		return result;
	}

	/**
	 * 获取
	 * @param
	 * @return
	 */
	public StatusRedisModel get(final StatusRedisModel statusRedisModel){
		StatusRedisModel result = redisTemplate.execute(new RedisCallback<StatusRedisModel>() {
			public StatusRedisModel doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] tempKey = serializer.serialize(statusRedisModel.getKey());
				byte[] value = connection.get(tempKey);
				if (value == null) {
					return null;
				}
				List<Integer> list = JSON.parseArray(serializer.deserialize(value),Integer.class);
				Set<Integer> set = new HashSet<Integer>();
				set.addAll(list);
				statusRedisModel.setStatusSet(set);
				return statusRedisModel;
			}
		});
		return result;
	}
	/**
	 * 根据项目名+key删除对象
	 * @param
	 */
	public void delete(StatusRedisModel statusRedisModel) {
		redisTemplate.delete(statusRedisModel.getKey());
	}

}
