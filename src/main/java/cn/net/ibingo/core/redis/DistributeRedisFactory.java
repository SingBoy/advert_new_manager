package cn.net.ibingo.core.redis;


import cn.net.ibingo.common.utils.RedisGenerator;
import cn.net.ibingo.core.redis.model.DistributeRedisModel;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

@Repository
public class DistributeRedisFactory extends RedisGenerator<String,String> {

	@Autowired
	RedisTemplate<String,String> redisTemplate;

	/**
	 * 添加FristChannel对象
	 * @param
	 * @return
	 */
	public void add(final DistributeRedisModel distributeRedisModel) {
		redisTemplate.execute(new RedisCallback<Void>() {
			public Void doInRedis(RedisConnection connection)
					throws DataAccessException{
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key  = serializer.serialize(distributeRedisModel.getKey());
				byte[] value = serializer.serialize(JSON.toJSONString(distributeRedisModel.getCount()));
				connection.set(key,value);
				return null;
			}
		});
	}

	/**
	 * 获取
	 * @param
	 * @return
	 */
	public Integer get(final DistributeRedisModel distributeRedisModel){
		Integer result = redisTemplate.execute(new RedisCallback<Integer>() {
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] tempKey = serializer.serialize(distributeRedisModel.getKey());
				byte[] value = connection.get(tempKey);
				if (value == null) {
					return null;
				}

				String str = serializer.deserialize(value);
				if(!StringUtils.isEmpty(str)) return Integer.valueOf(str);
				return null;

			}
		});
		return result;
	}

	/**
	 * 根据项目名+key删除对象
	 * @param
	 */
	public void delete( DistributeRedisModel distributeRedisModely) {
		redisTemplate.delete(distributeRedisModely.getKey());
	}

}
