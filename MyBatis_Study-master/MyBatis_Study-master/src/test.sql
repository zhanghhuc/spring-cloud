DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `items` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL COMMENT '��Ʒ����',
  `price` FLOAT(10,1) NOT NULL COMMENT '��Ʒ����',
  `detail` TEXT COMMENT '��Ʒ����',
  `pic` VARCHAR(64) DEFAULT NULL COMMENT '��ƷͼƬ',
  `createtime` DATETIME NOT NULL COMMENT '��������',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL COMMENT '�û�����',
  `birthday` DATE DEFAULT NULL COMMENT '����',
  `sex` CHAR(1) DEFAULT NULL COMMENT '�Ա�',
  `address` VARCHAR(256) DEFAULT NULL COMMENT '��ַ',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL COMMENT '�µ��û�id',
  `number` VARCHAR(32) NOT NULL COMMENT '������',
  `createtime` DATETIME NOT NULL COMMENT '��������ʱ��',
  `note` VARCHAR(100) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`id`),
  KEY `FK_orders_1` (`user_id`),
  CONSTRAINT `FK_orders_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orderdetail;
CREATE TABLE `orderdetail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `orders_id` INT(11) NOT NULL COMMENT '����id',
  `items_id` INT(11) NOT NULL COMMENT '��Ʒid',
  `items_num` INT(11) DEFAULT NULL COMMENT '��Ʒ��������',
  PRIMARY KEY (`id`),
  KEY `FK_orderdetail_1` (`orders_id`),
  KEY `FK_orderdetail_2` (`items_id`),
  CONSTRAINT `FK_orderdetail_1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orderdetail_2` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


/*Data for the table `items` */

INSERT  INTO `items`(`id`,`name`,`price`,`detail`,`pic`,`createtime`) VALUES (1,'̨ʽ��',3000.0,'�õ��������ǳ��ã�������',NULL,'2015-02-03 13:22:53'),(2,'�ʼǱ�',6000.0,'�ʼǱ����ܺã������ã���������',NULL,'2015-02-09 13:22:57'),(3,'����',200.0,'���Ʊ����������������ã�������',NULL,'2015-02-06 13:23:02');

/*Data for the table `user` */

INSERT  INTO `user`(`id`,`username`,`birthday`,`sex`,`address`) VALUES (1,'����',NULL,'2',NULL),(10,'����','2014-07-10','1','������'),(16,'��С��',NULL,'1','����֣��'),(22,'��С��',NULL,'1','����֣��'),(24,'������',NULL,'1','����֣��'),(25,'��С��',NULL,'1','����֣��'),(26,'����',NULL,NULL,NULL);

/*Data for the table `orders` */

INSERT  INTO `orders`(`id`,`user_id`,`number`,`createtime`,`note`) VALUES (3,1,'1000010','2015-02-04 13:22:35',NULL),(4,1,'1000011','2015-02-03 13:22:41',NULL),(5,10,'1000012','2015-02-12 16:13:23',NULL);

/*Data for the table `orderdetail` */

INSERT  INTO `orderdetail`(`id`,`orders_id`,`items_id`,`items_num`) VALUES (1,3,1,1),(2,3,2,3),(3,4,3,4),(4,4,2,3);
