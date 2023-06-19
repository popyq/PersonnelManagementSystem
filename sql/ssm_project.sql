/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : ssm_project

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 14/06/2023 09:08:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dep_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '办公地点',
  `status` int(0) NOT NULL DEFAULT 1 COMMENT '1代表有效,0代表无效',
  PRIMARY KEY (`dep_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '董事会', '九江浔阳区商会大厦1栋1层', 1);
INSERT INTO `department` VALUES (2, '经理办公室', '九江浔阳区商会大厦1栋2层', 1);
INSERT INTO `department` VALUES (3, '财务部', '九江浔阳区商会大厦1栋3层', 1);
INSERT INTO `department` VALUES (4, '销售部', '九江浔阳区商会大厦1栋4层', 1);
INSERT INTO `department` VALUES (5, '人事部', '九江浔阳区商会大厦1栋5层', 1);
INSERT INTO `department` VALUES (6, '行政部', '九江浔阳区商会大厦1栋6层', 1);
INSERT INTO `department` VALUES (7, '监督部', '九江浔阳区商会大厦1栋7层', 1);
INSERT INTO `department` VALUES (9, '安全部', '九江浔阳区商会大厦1栋9层', 1);
INSERT INTO `department` VALUES (10, '卫生部', '九江浔阳区商会大厦1栋10层', 1);
INSERT INTO `department` VALUES (11, '保卫部', '九江浔阳区商会大厦1栋11层', 1);
INSERT INTO `department` VALUES (12, '后勤部', '九江浔阳区商会大厦1栋12层', 1);
INSERT INTO `department` VALUES (13, '监督部', '九江浔阳区商会大厦1栋14层', 1);
INSERT INTO `department` VALUES (14, '培训部', '九江浔阳区商会大厦1栋15层', 1);
INSERT INTO `department` VALUES (85, '测试部门1', '测试地址1', 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `em_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `login_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `dep_id` int(0) NOT NULL COMMENT '部门id',
  `position_id` int(0) NOT NULL COMMENT '职位id',
  `status` int(0) NOT NULL DEFAULT 1 COMMENT '状态(0:离职 1:在职)',
  PRIMARY KEY (`em_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'aa52dcb2927262277a68a56f5455eeba', '码云', '11000000000', 1, 1, 1);
INSERT INTO `employee` VALUES (2, 'aa52dcb2927262277a68a56f5455eeba', '彭鱼晏', '12000000000', 1, 2, 1);
INSERT INTO `employee` VALUES (3, 'aa52dcb2927262277a68a56f5455eeba', '吴艳祖', '13000000000', 2, 3, 1);
INSERT INTO `employee` VALUES (4, 'aa52dcb2927262277a68a56f5455eeba', '张雪友', '14000000000', 2, 5, 1);
INSERT INTO `employee` VALUES (5, 'aa52dcb2927262277a68a56f5455eeba', 'admin', '15000000000', 5, 9, 1);
INSERT INTO `employee` VALUES (31, 'aa52dcb2927262277a68a56f5455eeba', '李雷', '123456', 6, 8, 1);

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `position_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '职位表id',
  `position_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职位名',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int(0) NOT NULL DEFAULT 1 COMMENT '状态 1:有效 0:无效',
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11264 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '职位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '董事长', '2023-06-02 15:17:36', 1);
INSERT INTO `position` VALUES (2, '副董事长', '2023-06-02 15:17:36', 1);
INSERT INTO `position` VALUES (3, '执行懂事长', '2023-05-31 10:39:23', 1);
INSERT INTO `position` VALUES (4, '总经理', '2023-05-31 10:39:29', 1);
INSERT INTO `position` VALUES (5, '副总经理', '2023-05-31 10:39:35', 1);
INSERT INTO `position` VALUES (6, '财务部经理', '2023-05-31 10:40:30', 1);
INSERT INTO `position` VALUES (7, '销售部经理', '2023-05-31 10:31:36', 1);
INSERT INTO `position` VALUES (8, '行政部经理', '2023-05-31 10:40:47', 1);
INSERT INTO `position` VALUES (9, '人事部经理', '2023-05-31 10:41:06', 1);
INSERT INTO `position` VALUES (10, '销售员', '2023-05-31 10:43:31', 1);
INSERT INTO `position` VALUES (11, '后勤部人员', '2023-06-09 15:16:58', 1);
INSERT INTO `position` VALUES (12, '会计', '2023-05-31 10:43:53', 1);
INSERT INTO `position` VALUES (13, '安全部人员', '2023-06-09 15:17:03', 1);
INSERT INTO `position` VALUES (14, '清洁工', '2023-06-09 15:04:17', 1);
INSERT INTO `position` VALUES (11264, '测试职位1', '2023-06-09 15:48:51', 1);

SET FOREIGN_KEY_CHECKS = 1;
