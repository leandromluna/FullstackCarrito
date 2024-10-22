CREATE TABLE IF NOT EXISTS users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       rol VARCHAR(50) NOT NULL,
                       voucher TINYINT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS product (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name_product VARCHAR(255),
                        price DECIMAL(10, 2),
                        stock INT,
                        image_url CLOB
);

CREATE TABLE IF NOT EXISTS special_date (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    start_date DATE NOT NULL,
                                    end_date DATE NOT NULL,
                                    discount DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS vip_history (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    start_vip DATE NOT NULL,
                    end_vip DATE,
                    user_id BIGINT
);