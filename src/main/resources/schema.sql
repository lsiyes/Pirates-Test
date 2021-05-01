DROP TABLE IF EXISTS fish_market_shop_holidays;
DROP TABLE IF EXISTS fish_market_shop_business_times;
DROP TABLE IF EXISTS fish_market_shop;

CREATE TABLE fish_market_shop (id IDENTITY
                    , name VARCHAR(15)
                    , owner VARCHAR(15)
                    , description VARCHAR(100)
                    , level BIGINT
                    , address VARCHAR(50)
                    , phone VARCHAR(13)
                    , holidays VARCHAR(100)
                    );

CREATE TABLE fish_market_shop_business_times (time_id IDENTITY
                    , id BIGINT
                    , day VARCHAR(10)
                    , open VARCHAR(5)
                    , close VARCHAR(5)
                    , FOREIGN KEY(id) REFERENCES fish_market_shop ON DELETE CASCADE
                    );

CREATE TABLE fish_market_shop_holidays (holiday_id IDENTITY
                    , id BIGINT
                    , holiday VARCHAR(10)
                    , FOREIGN KEY(id) REFERENCES fish_market_shop ON DELETE CASCADE
                    );