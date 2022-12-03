CREATE TABLE `Review` (
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `store_id` int,
                          `user_id` int,
                          `review_text` varchar(200),
                          `created_at` datetime,
                          `modified_at` datetime,
                          `removed_at` datetime
);

CREATE TABLE `Review_Tag` (
                              `id` int PRIMARY KEY AUTO_INCREMENT,
                              `tag_text` varchar(255),
                              `created_at` datetime,
                              `modified_at` datetime,
                              `removed_at` datetime
);

CREATE TABLE `Review_Tag_Log` (
                                  `id` int PRIMARY KEY AUTO_INCREMENT,
                                  `user_id` int,
                                  `store_id` int,
                                  `review_id` int,
                                  `review_tag_id` int,
                                  `created_at` datetime,
                                  `modified_at` datetime,
                                  `removed_at` datetime
);

CREATE TABLE `Item` (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `store_id` int,
                        `title` varchar(255),
                        `price` int,
                        `unit` ENUM('G', 'ML'),
                        `brand` varchar(255),
                        `category` varchar(255),
                        `img_path` varchar(255),
                        `created_at` datetime,
                        `modified_at` datetime,
                        `removed_at` datetime
);

CREATE TABLE `Img_Store` (
                             `id` int PRIMARY KEY AUTO_INCREMENT,
                             `store_id` int,
                             `path` varchar(255),
                             `created_at` datetime,
                             `modified_at` datetime,
                             `removed_at` datetime
);

CREATE TABLE `Img_Review` (
                              `id` int PRIMARY KEY AUTO_INCREMENT,
                              `review_id` int,
                              `path` varchar(255),
                              `created_at` datetime,
                              `modified_at` datetime,
                              `removed_at` datetime
);

CREATE TABLE `Category_Store` (
                                  `id` int PRIMARY KEY AUTO_INCREMENT,
                                  `store_id` int,
                                  `category_text` varchar(255),
                                  `created_at` datetime,
                                  `modified_at` datetime,
                                  `removed_at` datetime
);
