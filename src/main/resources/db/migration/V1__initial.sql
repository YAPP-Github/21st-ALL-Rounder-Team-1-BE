CREATE TABLE IF NOT EXISTS `User` (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `name` varchar(20),
                        `type` Enum('USER', 'BOSS'),
                        `oauth_type` Enum('KAKAO', 'APPLE'),
                        `oauth_identity` varchar(45),
                        `rating` tinyint,
                        `img_path` varchar(255),
                        `created_at` datetime,
                        `modified_at` datetime,
                        `removed_at` datetime
);