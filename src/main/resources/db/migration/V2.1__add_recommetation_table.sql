CREATE TABLE `Recommendation` (
                                  `id` int PRIMARY KEY AUTO_INCREMENT,
                                  `user_id` int,
                                  `store_id` int,
                                  `created_at` datetime,
                                  `modified_at` datetime,
                                  `removed_at` datetime
);