package cn.net.ibingo.common.pagination.component;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.tag.PaginationTag;

/**
 * 分页组件工厂类
 * 
 * @author LOMI
 *
 */
public class PaginationComponentFactory {

	private static final String NUMBER_TYPE_2 = "number2";
	private static final String BEYONDADMIN = "beyond";

	private static final String DEFAULT_TYPE = NUMBER_TYPE_2;

	private static Logger log = Logger.getLogger(PaginationComponentFactory.class);

	private static Map<String, Class<? extends PaginationComponent>> componentClazzMap = new HashMap<String, Class<? extends PaginationComponent>>();

	static {
		registerComponentClass(NUMBER_TYPE_2, Number2PaginationComponent.class);
		registerComponentClass(BEYONDADMIN, BeyondPaginationComponent.class);
	}

	public static void registerComponentClass(String type, Class<? extends PaginationComponent> clazz) {
		componentClazzMap.put(type, clazz);
	}

	public static PaginationComponent buildComponent(PaginationTag paginationTag, PaginationList<?> paginatedList) {
		Class<? extends PaginationComponent> clazz = componentClazzMap.get(paginationTag.getType());

		if (clazz == null) {
			clazz = componentClazzMap.get(DEFAULT_TYPE);
		}

		try {
			Constructor<? extends PaginationComponent> constructor = clazz.getConstructor(PaginationTag.class, PaginationList.class);
			return constructor.newInstance(paginationTag, paginatedList);
		} catch (Exception e) {
			log.error("构建分页组件失败", e);
		}
		return null;
	}

}
