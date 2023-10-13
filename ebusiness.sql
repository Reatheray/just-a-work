SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE ebusiness;

USE ebusiness;

-- ----------------------------
-- Table structure for ausertable
-- ----------------------------
DROP TABLE IF EXISTS `ausertable`;
CREATE TABLE `ausertable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员姓名',
  `apwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ausertable
-- ----------------------------
INSERT INTO `ausertable` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for busertable
-- ----------------------------
DROP TABLE IF EXISTS `busertable`;
CREATE TABLE `busertable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表',
  `bemail` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `bpwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busertable
-- ----------------------------
INSERT INTO `busertable` VALUES (9, 'chenheng@126.com', '78f8a7ae700c91db09facb05a675432b');

-- ----------------------------
-- Table structure for carttable
-- ----------------------------
DROP TABLE IF EXISTS `carttable`;
CREATE TABLE `carttable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busertable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  `shoppingnum` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bid`(`busertable_id`) USING BTREE,
  INDEX `gno`(`goodstable_id`) USING BTREE,
  CONSTRAINT `bid` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gno` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carttable
-- ----------------------------

-- ----------------------------
-- Table structure for focustable
-- ----------------------------
DROP TABLE IF EXISTS `focustable`;
CREATE TABLE `focustable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodstable_id` int(11) NOT NULL,
  `busertable_id` int(11) NOT NULL,
  `focustime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gno1`(`goodstable_id`) USING BTREE,
  INDEX `bid1`(`busertable_id`) USING BTREE,
  CONSTRAINT `bid1` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gno1` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of focustable
-- ----------------------------
INSERT INTO `focustable` VALUES (6, 47, 9, '2021-01-04 05:09:35');
INSERT INTO `focustable` VALUES (7, 36, 9, '2021-01-04 05:09:55');
INSERT INTO `focustable` VALUES (8, 45, 9, '2022-12-12 10:55:25');
INSERT INTO `focustable` VALUES (9, 46, 9, '2022-12-12 10:55:28');

-- ----------------------------
-- Table structure for goodstable
-- ----------------------------
DROP TABLE IF EXISTS `goodstable`;
CREATE TABLE `goodstable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `goprice` double NULL DEFAULT NULL,
  `grprice` double NULL DEFAULT NULL,
  `gstore` int(11) NULL DEFAULT NULL,
  `gpicture` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `isRecommend` tinyint(2) NULL DEFAULT NULL,
  `isAdvertisement` tinyint(2) NULL DEFAULT NULL,
  `goodstype_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `typeid`(`goodstype_id`) USING BTREE,
  CONSTRAINT `typeid` FOREIGN KEY (`goodstype_id`) REFERENCES `goodstype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstable
-- ----------------------------
INSERT INTO `goodstable` VALUES (36, '苹果1', 10, 8, 70, '20210103201023158.jpg', 1, 0, 19);
INSERT INTO `goodstable` VALUES (37, '衣服1', 20, 10, 100, '20210103201332703.jpg', 1, 1, 21);
INSERT INTO `goodstable` VALUES (38, '鲜花1', 20, 10, 200, '20210103201625494.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (39, '鲜花2', 30, 20, 300, '20210103201643847.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (40, '鲜花3', 50, 30, 400, '20210103201702145.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (41, '鲜花4', 50, 40, 300, '20210103201722153.jpg', 0, 1, 22);
INSERT INTO `goodstable` VALUES (42, '衣服11', 30, 20, 300, '20210103204253840.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (43, '衣服22', 50, 40, 600, '20210103204317014.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (44, '衣服33', 50, 40, 600, '20210103204336541.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (45, '衣服144', 50, 40, 400, '20210103204353659.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (46, '衣服155', 400, 300, 900, '20210103204450712.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (47, '衣服66', 80, 50, 719, '202210275105546950.jpg', 1, 0, 18);
INSERT INTO `goodstable` VALUES (48, '衬衫', 120, 110, 20, '202305137235104228.jpg', 1, 0, 21);

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (18, '家电');
INSERT INTO `goodstype` VALUES (19, '水果');
INSERT INTO `goodstype` VALUES (20, '文具');
INSERT INTO `goodstype` VALUES (21, '服装');
INSERT INTO `goodstype` VALUES (22, '鲜花');
INSERT INTO `goodstype` VALUES (24, '书籍');

-- ----------------------------
-- Table structure for orderbasetable
-- ----------------------------
DROP TABLE IF EXISTS `orderbasetable`;
CREATE TABLE `orderbasetable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busertable_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `orderdate` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bid2`(`busertable_id`) USING BTREE,
  CONSTRAINT `bid2` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderbasetable
-- ----------------------------
INSERT INTO `orderbasetable` VALUES (6, 9, 2740, 0, '2021-01-04 05:11:23');
INSERT INTO `orderbasetable` VALUES (7, 9, 1050, 1, '2022-12-07 20:17:17');
INSERT INTO `orderbasetable` VALUES (8, 9, 500, 1, '2022-12-12 10:55:50');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderbasetable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  `shoppingnum` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `odsn`(`orderbasetable_id`) USING BTREE,
  INDEX `gno3`(`goodstable_id`) USING BTREE,
  CONSTRAINT `gno3` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `odsn` FOREIGN KEY (`orderbasetable_id`) REFERENCES `orderbasetable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES (7, 6, 47, 50);
INSERT INTO `orderdetail` VALUES (8, 6, 36, 30);
INSERT INTO `orderdetail` VALUES (9, 7, 47, 21);
INSERT INTO `orderdetail` VALUES (10, 8, 47, 10);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` tinyint(2) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `usex` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `upass` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '女', '123456');
INSERT INTO `user` VALUES (12, 'xiaoli', '男', '1234');
INSERT INTO `user` VALUES (31, '李四', '男', '123456');
INSERT INTO `user` VALUES (33, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (34, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (35, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (36, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (37, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (38, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (39, '33', NULL, NULL);
INSERT INTO `user` VALUES (40, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (41, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (42, 'chenheng2', '男', NULL);
INSERT INTO `user` VALUES (44, 'chenheng2', '女', '1234');
INSERT INTO `user` VALUES (45, 'chenheng3', '男', '1234');
INSERT INTO `user` VALUES (46, 'chenheng4', '女', '1234');
INSERT INTO `user` VALUES (47, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (48, 'chenheng2', '女', '1234');
INSERT INTO `user` VALUES (49, 'chenheng3', '男', '1234');
INSERT INTO `user` VALUES (50, 'chenheng4', '女', '1234');
INSERT INTO `user` VALUES (51, '小李', '男', '1234');
INSERT INTO `user` VALUES (52, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (53, 'chenheng2', '女', '1234');
INSERT INTO `user` VALUES (54, 'chenheng3', '男', '1234');
INSERT INTO `user` VALUES (55, 'chenheng4', '女', '1234');
INSERT INTO `user` VALUES (56, 'Xu jiaxi', NULL, 'rrr');
INSERT INTO `user` VALUES (57, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (58, 'chenheng2', '女', '1234');
INSERT INTO `user` VALUES (59, 'chenheng3', '男', '1234');
INSERT INTO `user` VALUES (60, 'chenheng4', '女', '1234');
INSERT INTO `user` VALUES (61, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (62, 'chenheng2', '女', '1234');
INSERT INTO `user` VALUES (63, 'chenheng3', '男', '1234');
INSERT INTO `user` VALUES (64, 'chenheng4', '女', '1234');
INSERT INTO `user` VALUES (65, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (66, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (67, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (68, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (69, 'chenheng1', '男', '1234');
INSERT INTO `user` VALUES (70, 'kk', NULL, '1234');
INSERT INTO `user` VALUES (71, 'ff', NULL, 'ff');
INSERT INTO `user` VALUES (72, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (73, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (74, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (75, '陈恒', '男', NULL);
INSERT INTO `user` VALUES (76, '陈恒主键回填545', '男', NULL);
INSERT INTO `user` VALUES (77, '陈恒主键回填957', '男', NULL);

SET FOREIGN_KEY_CHECKS = 1;
