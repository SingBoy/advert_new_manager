USE `out_sdk`;

/*Table structure for table `t_carrier` */

DROP TABLE IF EXISTS `t_carrier`;

CREATE TABLE `t_carrier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '���',
  `name` varchar(300) NOT NULL COMMENT '����',
  `country_group` varchar(700) DEFAULT NULL COMMENT '�������(�Զ��ŷָ�)',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `modify_date` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='��Ӫ����Ϣ';

/*Table structure for table `t_country` */

DROP TABLE IF EXISTS `t_country`;

CREATE TABLE `t_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '���Ҵ���',
  `name` varchar(300) NOT NULL COMMENT '��������',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `modify_date` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='������Ϣ';

/*Table structure for table `t_country_carrier` */

DROP TABLE IF EXISTS `t_country_carrier`;

CREATE TABLE `t_country_carrier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) NOT NULL COMMENT '����ID',
  `carrier_id` int(11) NOT NULL COMMENT '��Ӫ��ID',
  PRIMARY KEY (`id`),
  KEY `t_country_carrier_countryId` (`country_id`),
  KEY `t_country_carrier_carrierId` (`carrier_id`),
  CONSTRAINT `t_country_carrier_countryId` FOREIGN KEY (`country_id`) REFERENCES `t_country` (`id`),
  CONSTRAINT `t_country_carrier_carrierId` FOREIGN KEY (`carrier_id`) REFERENCES `t_carrier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='������Ӫ�̹�����Ϣ';

/*Table structure for table `t_detail_sdk` */

DROP TABLE IF EXISTS `t_detail_sdk`;

CREATE TABLE `t_detail_sdk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sdk_id` int(11) NOT NULL COMMENT 'SDKID',
  `country_id` int(11) NOT NULL COMMENT '����ID',
  `carrier_id` int(11) NOT NULL COMMENT '��Ӫ��ID',
  `weight` int(11) DEFAULT '100' COMMENT 'Ȩ��',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `modify_date` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  KEY `t_detail_sdk_countryId` (`country_id`),
  KEY `t_detail_sdk_carrierId` (`carrier_id`),
  CONSTRAINT `t_detail_sdk_carrierId` FOREIGN KEY (`carrier_id`) REFERENCES `t_carrier` (`id`),
  CONSTRAINT `t_detail_sdk_countryId` FOREIGN KEY (`country_id`) REFERENCES `t_country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8 COMMENT='SDK������Ϣ';

/*Table structure for table `t_login_record` */

DROP TABLE IF EXISTS `t_login_record`;

CREATE TABLE `t_login_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '�û���',
  `ip` varchar(200) NOT NULL COMMENT 'ip',
  `create_date` datetime NOT NULL COMMENT '��¼ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=utf8;

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_package` varchar(200) NOT NULL COMMENT '��Ʒ����',
  `product_name` varchar(200) NOT NULL COMMENT '��Ʒ����',
  `status` int(1) NOT NULL COMMENT '��Ʒ״̬(0�����ߣ�1������)',
  `sdk_id` int(11) DEFAULT NULL COMMENT 'SDK ID',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `modify_date` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  KEY `t_product_sdkId` (`sdk_id`),
  CONSTRAINT `t_product_sdkId` FOREIGN KEY (`sdk_id`) REFERENCES `t_rough_sdk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='��Ʒ��Ϣ';

/*Table structure for table `t_rough_sdk` */

DROP TABLE IF EXISTS `t_rough_sdk`;

CREATE TABLE `t_rough_sdk` (
  `id` int(11) NOT NULL COMMENT 'SDK����',
  `sdk_name` varchar(100) NOT NULL COMMENT 'SDK����',
  `sdk_provide` varchar(100) NOT NULL COMMENT 'SDK�ṩ��',
  `country_group` varchar(700) DEFAULT NULL COMMENT 'SDKͶ�Ź������(�Զ��ŷָ�)',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `modify_date` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SDK��Ϣ';

/*Table structure for table `t_sys_record` */

DROP TABLE IF EXISTS `t_sys_record`;

CREATE TABLE `t_sys_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(200) DEFAULT NULL COMMENT '������IP',
  `name` varchar(200) DEFAULT NULL COMMENT '������Ա',
  `module` varchar(200) DEFAULT NULL COMMENT '����ģ��\n1���û�����\n2����ɫ����\n3��Ȩ�޹���\n4��ϵͳ����\n5���豸����\n6���ͻ�����\n7�����ҹ���\n8�����������\n9�����������\n10����Դ����\n11��DDL��Դ\n12��DDL����',
  `method` varchar(200) DEFAULT NULL COMMENT '������ʽ\n1�����\n2���޸�\n3��ɾ��\n4����Ȩ',
  `content` text COMMENT '��������',
  `create_date` datetime DEFAULT NULL COMMENT '��������',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2625 DEFAULT CHARSET=utf8 COMMENT='ϵͳ������־��';

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