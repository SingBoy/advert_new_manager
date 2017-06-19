USE `out_sdk`;

/*Table structure for table `t_carrier` */

DROP TABLE IF EXISTS `t_carrier`;

CREATE TABLE `t_carrier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '编号',
  `name` varchar(300) NOT NULL COMMENT '名称',
  `country_group` varchar(700) DEFAULT NULL COMMENT '国家组合(以逗号分隔)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='运营商信息';

/*Table structure for table `t_country` */

DROP TABLE IF EXISTS `t_country`;

CREATE TABLE `t_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '国家代码',
  `name` varchar(300) NOT NULL COMMENT '国家名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='国家信息';

/*Table structure for table `t_country_carrier` */

DROP TABLE IF EXISTS `t_country_carrier`;

CREATE TABLE `t_country_carrier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) NOT NULL COMMENT '国家ID',
  `carrier_id` int(11) NOT NULL COMMENT '运营商ID',
  PRIMARY KEY (`id`),
  KEY `t_country_carrier_countryId` (`country_id`),
  KEY `t_country_carrier_carrierId` (`carrier_id`),
  CONSTRAINT `t_country_carrier_countryId` FOREIGN KEY (`country_id`) REFERENCES `t_country` (`id`),
  CONSTRAINT `t_country_carrier_carrierId` FOREIGN KEY (`carrier_id`) REFERENCES `t_carrier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='国家运营商关联信息';

/*Table structure for table `t_detail_sdk` */

DROP TABLE IF EXISTS `t_detail_sdk`;

CREATE TABLE `t_detail_sdk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sdk_id` int(11) NOT NULL COMMENT 'SDKID',
  `country_id` int(11) NOT NULL COMMENT '国家ID',
  `carrier_id` int(11) NOT NULL COMMENT '运营商ID',
  `weight` int(11) DEFAULT '100' COMMENT '权重',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `t_detail_sdk_countryId` (`country_id`),
  KEY `t_detail_sdk_carrierId` (`carrier_id`),
  CONSTRAINT `t_detail_sdk_carrierId` FOREIGN KEY (`carrier_id`) REFERENCES `t_carrier` (`id`),
  CONSTRAINT `t_detail_sdk_countryId` FOREIGN KEY (`country_id`) REFERENCES `t_country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8 COMMENT='SDK配置信息';

/*Table structure for table `t_login_record` */

DROP TABLE IF EXISTS `t_login_record`;

CREATE TABLE `t_login_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `ip` varchar(200) NOT NULL COMMENT 'ip',
  `create_date` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=utf8;

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_package` varchar(200) NOT NULL COMMENT '产品包名',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `status` int(1) NOT NULL COMMENT '产品状态(0：上线，1：下线)',
  `sdk_id` int(11) DEFAULT NULL COMMENT 'SDK ID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `t_product_sdkId` (`sdk_id`),
  CONSTRAINT `t_product_sdkId` FOREIGN KEY (`sdk_id`) REFERENCES `t_rough_sdk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='产品信息';

/*Table structure for table `t_rough_sdk` */

DROP TABLE IF EXISTS `t_rough_sdk`;

CREATE TABLE `t_rough_sdk` (
  `id` int(11) NOT NULL COMMENT 'SDK编码',
  `sdk_name` varchar(100) NOT NULL COMMENT 'SDK名称',
  `sdk_provide` varchar(100) NOT NULL COMMENT 'SDK提供方',
  `country_group` varchar(700) DEFAULT NULL COMMENT 'SDK投放国家组合(以逗号分隔)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SDK信息';

/*Table structure for table `t_sys_record` */

DROP TABLE IF EXISTS `t_sys_record`;

CREATE TABLE `t_sys_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(200) DEFAULT NULL COMMENT '操作者IP',
  `name` varchar(200) DEFAULT NULL COMMENT '操作人员',
  `module` varchar(200) DEFAULT NULL COMMENT '操作模块\n1：用户管理\n2：角色管理\n3：权限管理\n4：系统配置\n5：设备过滤\n6：客户管理\n7：国家管理\n8：主排序管理\n9：包排序管理\n10：资源过滤\n11：DDL资源\n12：DDL翻译',
  `method` varchar(200) DEFAULT NULL COMMENT '操作方式\n1：添加\n2：修改\n3：删除\n4：授权',
  `content` text COMMENT '操作内容',
  `create_date` datetime DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2625 DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `c_datetime` datetime DEFAULT NULL,
  `m_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;