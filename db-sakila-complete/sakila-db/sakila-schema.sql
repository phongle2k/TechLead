-- Sakila Sample Database Schema
-- Version 1.4

-- Copyright (c) 2006, 2023, Oracle and/or its affiliates.

-- Redistribution and use in source and binary forms, with or without
-- modification, are permitted provided that the following conditions are
-- met:

-- * Redistributions of source code must retain the above copyright notice,
--   this list of conditions and the following disclaimer.
-- * Redistributions in binary form must reproduce the above copyright
--   notice, this list of conditions and the following disclaimer in the
--   documentation and/or other materials provided with the distribution.
-- * Neither the name of Oracle nor the names of its contributors may be used
--   to endorse or promote products derived from this software without
--   specific prior written permission.

-- THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
-- IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
-- THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
-- PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
-- CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
-- EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
-- PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
-- PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
-- LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
-- NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
-- SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

SET NAMES utf8mb4;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS sakila;
CREATE SCHEMA sakila;
USE sakila;

--
-- Table structure for table `actor`
--

CREATE TABLE actor (
  actor_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (actor_id),
  KEY idx_actor_last_name (last_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 1. Viết truy vấn SQL để trả về họ và tên của tất cả các diễn viên trong cơ sở dữ liệu.
select concat(first_name,' ',last_name) as fullname from actor;

--
-- Table structure for table `address`
--

CREATE TABLE address (
  address_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  address VARCHAR(50) NOT NULL,
  address2 VARCHAR(50) DEFAULT NULL,
  district VARCHAR(20) NOT NULL,
  city_id SMALLINT UNSIGNED NOT NULL,
  postal_code VARCHAR(10) DEFAULT NULL,
  phone VARCHAR(20) NOT NULL,
  -- Add GEOMETRY column for MySQL 5.7.5 and higher
  -- Also include SRID attribute for MySQL 8.0.3 and higher
  /*!50705 location GEOMETRY */ /*!80003 SRID 0 */ /*!50705 NOT NULL,*/
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (address_id),
  KEY idx_fk_city_id (city_id),
  /*!50705 SPATIAL KEY `idx_location` (location),*/
  CONSTRAINT `fk_address_city` FOREIGN KEY (city_id) REFERENCES city (city_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `category`
--

CREATE TABLE category (
  category_id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `city`
--

CREATE TABLE city (
  city_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  city VARCHAR(50) NOT NULL,
  country_id SMALLINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (city_id),
  KEY idx_fk_country_id (country_id),
  CONSTRAINT `fk_city_country` FOREIGN KEY (country_id) REFERENCES country (country_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `country`
--

CREATE TABLE country (
  country_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  country VARCHAR(50) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (country_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `customer`
-- 5. Viết truy vấn SQL để trả về tên và địa chỉ của tất cả khách hàng đã thuê phim trong tháng 1 năm 2022.
SELECT c.first_name, c.last_name, c.address_id
FROM customer c
JOIN rental r ON c.customer_id = r.customer_id
WHERE r.rental_date >= '2006-02-01' AND r.rental_date < '2006-03-01';

-- 6. Viết truy vấn SQL để trả về doanh thu do mỗi cửa hàng tạo ra trong cơ sở dữ liệu cho năm 2021.
SELECT s.store_id, SUM(p.amount) AS total_revenue
FROM store s
JOIN staff st ON s.store_id = st.store_id
 JOIN rental r ON st.staff_id = r.staff_id
 JOIN payment p ON r.rental_id = p.rental_id
WHERE p.payment_date >= '2006-02-01' AND p.payment_date < '2006-03-01'
GROUP BY s.store_id;

-- 7. Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong hơn 20 bộ phim trong cơ sở dữ liệu.
-- liên kết bảng actor - actor_id, film -> film_id, film -> film_id
Select a.actor_id, a.first_name, a.last_name
from actor a 
Join film_actor fa ON a.actor_id = fa.actor_id 
Join film f ON fa.film_id = f.film_id
group by a.actor_id, a.first_name, a.last_name
Having count(f.film_id) > 20 ;
-- 8. Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'PG-13' và thời lượng hơn 120 phút.
select title, rating, length from film 
where rating = 'PG-13' AND length > 120;
-- Cấp độ 2:
-- 1. Viết truy vấn SQL để trả về 10 khách hàng hàng đầu đã tạo ra nhiều doanh thu nhất cho cửa hàng, bao gồm tên của họ và tổng doanh thu được tạo ra.
select c.first_name, c.last_name, SUM(p.amount) as sum_amount -- lấy ra tên, họ => từ bảng customer, tổng doanh thu gán cho sum_amount => từ bảng payment
from customer c 
join payment p ON c.customer_id = p.customer_id -- bảng payment có chứ customer_id
group by c.customer_id , c.first_name, c.last_name
order by sum_amount desc -- tổng cao nhất giảm dần
limit 10; -- giới hạn 10 bản ghi
-- 2. Viết truy vấn SQL để trả về tên và thông tin liên hệ của tất cả khách hàng đã thuê phim ở tất cả các danh mục trong cơ sở dữ liệu.
select distinct c.first_name, c.last_name, c.email 
from customer c
join rental r on c.customer_id = r.customer_id
join inventory i on r.inventory_id = i.inventory_id
join film f on i.film_id = f.film_id
join film_category fc on f.film_id = fc.film_id
join category ct on fc.category_id = ct.category_id;
-- 3. Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê ít nhất một lần nhưng không bao giờ trả lại.
select title, r.rental_date 
from film f
left join inventory i on f.film_id = i.film_id -- dùng left join cho phép lấy tất cả các bộ phim dù có bản sao trong bảng hay không?
left join rental r on i.inventory_id = r.inventory_id -- dùng left join cho phép lấy all các giao dịch phim dù có gd thuê tương ứng hay k.
where r.rental_date is null;
-- 4. Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim trong mỗi danh mục trong cơ sở dữ liệu.
select a.actor_id, a.last_name, a.first_name
from actor a
join film_actor fa on a.actor_id = fa.actor_id
join film f on fa.film_id = f.film_id
join film_category fc on f.film_id = fc.film_id
group by a.actor_id, a.last_name, a.first_name
having count(distinct fc.category_id) = (select count(*) from category); 
/* Giải thích Having: thực hiện bằng cách so sánh số danh mục phim khác nhau mà mỗi diễn viên đã xuất hiện (COUNT(DISTINCT fc.category_id)) với tổng số danh mục phim 
có trong bảng categories. Nếu số này bằng tổng số danh mục phim, tức là diễn viên đã xuất hiện trong ít nhất một bộ phim trong mỗi danh mục, 
thì kết quả của diễn viên này sẽ được lấy. */
-- 5. Viết một truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê cùng một bộ phim nhiều lần trong một giao dịch, cùng với số lần họ đã thuê bộ phim đó.
select c.customer_id, c.first_name, c.last_name, r.rental_date, count(r.rental_id) as time_rents 
from customer c
join rental r on c.customer_id = r.customer_id
join inventory i on r.inventory_id = i.inventory_id
join film f on i.film_id = f.film_id
group by c.customer_id, c.first_name, c.last_name, r.rental_date
having count(r.rental_id) > 1;
-- 6. Viết truy vấn SQL để trả về tổng doanh thu do mỗi diễn viên tạo ra trong cơ sở dữ liệu, dựa trên phí thuê phim mà họ đã xuất hiện.
select a.actor_id, a.first_name, a.last_name, sum(p.amount) as sum_amount
from actor a
join film_actor fa on a.actor_id = fa.actor_id
join film f on fa.film_id = f.film_id 
join inventory i on f.film_id = i.film_id
join rental r on i.inventory_id = r.inventory_id
join payment p on r.rental_id = p.rental_id
group by a.actor_id, a.first_name, a.last_name;
/* 7. Viết một truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim có xếp hạng 'R', 
nhưng chưa bao giờ xuất hiện trong một bộ phim có xếp hạng 'G'. */
select a.actor_id, a.first_name, a.last_name, f.rating
from actor a
join film_actor fa on a.actor_id = fa.actor_id
join film f on fa.film_id = f.film_id
where f.rating = 'R' AND NOT f.rating = 'G';
/* 8. Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê bởi hơn 50 khách hàng, 
nhưng chưa bao giờ được thuê bởi cùng một khách hàng nhiều lần. */
Select f.title 
from film f
join inventory i on f.film_id = i.film_id
join rental r on i.inventory_id = r.inventory_id
join customer c on r.customer_id = c.customer_id
group by f.film_id, f.title
having count(distinct r.customer_id) > 50
	and count(r.rental_id) = count(distinct r.customer_id);
-- 9. Viết truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê phim từ danh mục mà họ chưa từng thuê trước đây.
select c.customer_id, c.first_name, c.last_name
from customer c
join rental r on c.customer_id = r.customer_id
join inventory i on r.inventory_id = i.inventory_id
join film_category fc on i.film_id = fc.film_id
join category ct on fc.category_id = ct.category_id
group by c.customer_id, c.first_name, c.last_name
having count(ct.category_id) > 0;
-- 10. Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê bởi mọi khách hàng đã từng thuê phim từ danh mục 'Hành động'.
select f.title, c.name
from film f
join film_category fc on f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
where c.name = 'Action';
/* Cấp 3: 
1. Viết truy vấn SQL để trả về thời lượng thuê trung bình cho từng tổ hợp diễn viên và danh mục trong cơ sở dữ liệu, 
ngoại trừ các diễn viên chưa xuất hiện trong bất kỳ phim nào trong danh mục. */
Select a.actor_id, a.first_name, a.last_name, ct.category_id, ct.name as category_name, AVG(f.length) as AVG_rental
from actor a
join film_actor fa on a.actor_id = fa.actor_id
join film f on fa.film_id = f.film_id
join film_category fc on f.film_id = fc.film_id
join category ct on fc.category_id = ct.category_id
join inventory i on f.film_id = i.film_id
join rental r on i.inventory_id = r.inventory_id
-- ngoại trừ các diễn viên chưa xuất hiện trong bất kỳ phim nào trong danh mục.
where a.actor_id not in (
	Select distinct fa.actor_id
    from film_actor fa
    join film f on fa.film_id = f.film_id
    join film_category fc on f.film_id = fc.film_id
    join category ct on fc.category_id = ct.category_id
    where ct.category_id not in (
		Select distinct category_id
        from film_category
    )
)
group by a.actor_id, a.first_name, a.last_name, ct.category_id, ct.name
having count(distinct f.film_id) > 0;
/* 2. Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong một bộ phim có xếp hạng 'R' và thời lượng hơn 2 giờ, 
nhưng chưa bao giờ xuất hiện trong một bộ phim có xếp hạng 'G'. */
select a.actor_id, a.first_name, a.last_name, f.length, f.rating
from actor a
join film_actor fa on a.actor_id = fa.actor_id
join film f on fa.film_id = f.film_id
where ( f.rating = 'R' AND f.length > 120 ) AND NOT (f.rating = 'G');
-- 3. Viết truy vấn SQL để trả về tên của tất cả khách hàng đã thuê hơn 10 bộ phim trong một giao dịch, cùng với số lượng phim họ đã thuê và tổng phí thuê.
select c.customer_id, c.first_name, c.last_name, count(distinct f.film_id) as SoLuongPhimDaThue, sum(p.amount) as TongChiPhiThuePhim
from customer c
join payment p on c.customer_id = p.customer_id
join rental r on p.rental_id = r.rental_id
join inventory i on r.inventory_id = i.inventory_id
join film f on i.film_id = f.film_id
group by c.customer_id, c.first_name, c.last_name
having count(r.rental_id) > 10;
-- 4. Viết truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê mọi bộ phim trong một danh mục, cùng với tổng số phim đã thuê và tổng phí thuê.
select c.customer_id, c.first_name, c.last_name, count(distinct f.film_id) as TongSLPhimDaThue, sum(p.amount) as TongCPThue
from customer c
join payment p on c.customer_id = p.customer_id
join rental r on p.rental_id = r.rental_id
join inventory i on r.inventory_id = i.inventory_id
join film f on i.film_id = f.film_id
join film_category fc on f.film_id = fc.film_id
join category ct on fc.category_id = ct.category_id
group by c.customer_id, c.first_name, c.last_name;
/* 5. Viết một truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được cùng một khách hàng thuê nhiều lần trong một ngày, 
cùng với tên của những khách hàng đã thuê phim và số lần họ được thuê */
select f.title, c.customer_id, c.first_name, c.last_name, count(*) as SoLanThue
from customer c
join rental r on c.customer_id = r.customer_id
join inventory i on r.inventory_id = i.inventory_id
join film f on i.film_id = f.film_id
where date(r.rental_date) = '2005-05-07'
group by f.title, c.customer_id, c.first_name, c.last_name
having count(*) > 1;
/* 6. Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong ít nhất một bộ phim cùng với mọi diễn viên khác trong cơ sở dữ liệu, 
cùng với số lượng phim họ đã xuất hiện cùng nhau. */
select a1.actor_id, a1.first_name, a1.last_name, -- tao actor 1
	   a2.actor_id, a2.first_name, a2.last_name, -- tao actor 2
	   count(distinct fa1.film_id) as SoLuong 
from actor a1
join film_actor fa1 on a1.actor_id = fa1.actor_id
join film f1 on fa1.film_id = f1.film_id
join film_actor fa2 on fa1.film_id = fa2.film_id
join actor a2 on fa2.actor_id = a2.actor_id
where a1.actor_id <> a2.actor_id -- so sanh 
group by a1.actor_id, a1.first_name, a1.last_name, 
	   a2.actor_id, a2.first_name, a2.last_name
having count(distinct fa1.film_id) > 1;
-- 7. Viết truy vấn SQL để trả về tên của tất cả khách hàng đã thuê ít nhất một phim từ mỗi danh mục trong cơ sở dữ liệu, 
-- cùng với số lượng phim đã thuê từ mỗi danh mục.
select c.customer_id, c.first_name, c.last_name, count(distinct f.film_id) as soluong
from customer c
join rental r on c.customer_id = r.customer_id
join inventory i on r.inventory_id = i.inventory_id
join film f on i.film_id = f.film_id
join film_category fc on f.film_id = fc.film_id
join category ct on fc.category_id = ct.category_id
group by c.customer_id, c.first_name, c.last_name
having count(distinct fc.category_id) = (select count(category_id) from category);
-- 8. Viết truy vấn SQL để trả về tiêu đề của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 100 lần, nhưng chưa bao giờ được thuê 
-- bởi bất kỳ khách hàng nào đã thuê phim có xếp hạng 'G'.
select f.film_id, f.title
from film f
join inventory i on f.film_id = i.film_id
join rental r on i.inventory_id = r.inventory_id
where r.customer_id not in (
	select distinct r2.customer_id
    from rental r2
    join inventory i2 on r2.inventory_id = i2.inventory_id
    join film f2 on i2.film_id = f2.film_id
    where f2.rating = 'G'
)
group by f.film_id, f.title
having count(distinct r.rental_id) > 100;
-- 9. Viết truy vấn SQL để trả về tên của tất cả các khách hàng đã thuê phim từ danh mục mà họ chưa bao giờ thuê trước đây và cũng chưa bao giờ 
-- thuê phim dài hơn 3 giờ.
select c.customer_id, c.first_name, c.last_name 
from customer c;


-- 10. Viết truy vấn SQL để trả về tên của tất cả các diễn viên đã xuất hiện trong một bộ phim có xếp hạng 'PG-13' và thời lượng hơn 2 giờ, 
-- đồng thời cũng đã xuất hiện trong một bộ phim có xếp hạng 'R' và thời lượng dưới 90 phút.
select a.actor_id, a.first_name, a.last_name
from actor a
join film_actor fa1 on a.actor_id = fa1.actor_id
join film f1 on fa1.film_id = f1.film_id
join film_actor fa2 on a.actor_id = fa2.actor_id
join film f2 on fa2.film_id = f2.film_id
where (f1.rating = 'PG-13' AND f1.length > 120) AND (f2.rating = 'R' AND f2.length < 90)
group by a.actor_id, a.first_name, a.last_name;
/* Cấp 4 */
-- 1. Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 100 lần, đặt giá thuê mới cao hơn 10% so với giá hiện tại.
SET SQL_SAFE_UPDATES=0;
Update film
join (
	select f.film_id
    from film f
    join inventory i on f.film_id = i.film_id
    join rental r on i.inventory_id = r.inventory_id
    group by f.film_id
    having count(r.rental_id) > 100
) As new_amount on film.film_id = new_amount.film_id
set rental_rate = rental_rate * 1.1;
-- 2. Viết truy vấn SQL để cập nhật thời lượng thuê của tất cả các phim trong cơ sở dữ liệu đã được thuê hơn 5 lần, 
-- đặt thời lượng mới dài hơn 5% so với thời lượng hiện tại.
SET SQL_SAFE_UPDATES=0;
Update film
join (
	select f.film_id
    from film f
    join inventory i on f.film_id = i.film_id
    join rental r on i.inventory_id = r.inventory_id
    group by f.film_id
    having count(r.rental_id) > 100
) as new_duration on film.film_id = new_duration.film_id
set rental_duration = rental_duration * 1.05;
/* 3. Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong danh mục 'Hành động' được phát hành trước năm 2005, 
đặt giá mới cao hơn 20% so với giá hiện tại. */
update film
join (
	select f.film_id
    from film f
    join film_category fc on f.film_id = fc.film_id
    join category ct on fc.category_id = ct.category_id
    join inventory i on f.film_id = i.film_id
    join rental r on i.inventory_id = r.inventory_id
    where r.rental_date > '2005-01-01' and ct.name = 'Action'
    group by f.film_id
) as new_rental_date on film.film_id = new_rental_date.film_id
set rental_rate = rental_rate * 1.2;
-- 4. Viết truy vấn SQL để cập nhật địa chỉ email của tất cả cuả những người đã thuê một bộ phim thuộc danh mục 'Kinh dị' vào tháng 10 năm 2022, 
-- đặt địa chỉ email mới là sự kết hợp giữa địa chỉ email hiện tại của họ và chuỗi 'kinh dị'.
-- có thể làm theo bài 3. Cách làm mới so với bài 3
update customer
set email = concat(email, 'kinhdi')
where (
	select c.customer_id
    from customer c
    join rental r on c.customer_id = r.customer_id
    join inventory i on r.inventory_id = i.inventory_id
    join film_category fc on i.film_id = fc.film_id
    join category ct on fc.category_id = ct.category_id
    where ct.name = 'action' and (r.rental_date >= '2005-10-01' and r.rental_date <= '2005-10-31')
    group by c.customer_id
); 
-- 5. Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu đã được hơn 10 khách hàng thuê, đặt giá mới cao hơn 5% so 
-- với giá hiện tại, nhưng không cao hơn $4,00.
update film
set rental_rate = least(rental_rate * 1.05, 4)
where film_id in (
	select f.film_id
    from film f
    join inventory i on f.film_id = i.film_id
    join rental r on i.inventory_id = r.inventory_id
    group by f.film_id
    having count(distinct r.rental_id) > 10
);
-- 6. Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'PG-13' và thời lượng hơn 2 giờ, đặt giá mới là $3,5.
update film
set rental_rate = 3.5
where film_id in (
	select f.film_id
    from film f
    join film_category fc on f.film_id = fc.film_id
    join category ct on fc.category_id = ct.category_id
    where ct.name = 'PG-13' and length > 120
    group by f.film_id
);
-- 7. Viết truy vấn SQL để cập nhật thời lượng cho thuê của tất cả các phim trong danh mục 'Khoa học viễn tưởng' được phát hành vào năm 2010, 
-- đặt thời lượng mới bằng với thời lượng của phim tính bằng phút.
update film
set rental_duration = length
where film_id in (
	select f.film_id
    from film f 
    join film_category fc on f.film_id = fc.film_id
    join category ct on fc.category_id = ct.category_id
    where ct.name = 'Sci-fi' and f.release_year = 2010
    group by f.film_id
);
-- 8. Viết truy vấn SQL để cập nhật địa chỉ của tất cả các khách hàng sống trong cùng thành phố với một khách hàng khác có cùng họ, 
-- đặt địa chỉ mới là phần nối của địa chỉ hiện tại của họ và chuỗi 'same city'.

-- 9. Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong danh mục 'Hài kịch' được phát hành vào năm 2007 trở đi, 
-- đặt giá mới thấp hơn 15% so với giá hiện tại.
update film
set rental_rate = rental_rate / 1.15
where f.film_id in (
	select f.film_id
    from film f
    join film_category fc on f.film_id = fc.film_id
    join category ct on fc.category_id = ct.category_id
    where ct.name = 'Comedy' and release_year > 2010
    group by f.film_id
);
-- 10. Viết truy vấn SQL để cập nhật giá thuê của tất cả các phim trong cơ sở dữ liệu có xếp hạng 'G' và thời lượng dưới 1 giờ, đặt giá mới là $1,50.
update film
set rental_rate = 1.5
where rating = 'G' and length < 60;
-- 11. Viết truy vấn SQL để cập nhật mức lương của tất cả nhân viên trong cơ sở dữ liệu dựa trên chức danh công việc và số năm kinh nghiệm của họ, 
-- đặt mức lương mới bằng với mức lương hiện tại nhân với hệ số phụ thuộc vào chức danh công việc và số năm kinh nghiệm của họ.
/* Không tìm được dữ liệu của nhân viên */
UPDATE employees
SET current_salary = 
    CASE
        WHEN job_title = 'Manager' THEN current_salary * 1.1    -- Hệ số tăng 10% cho chức danh Manager
        WHEN job_title = 'Senior Engineer' THEN current_salary * 1.08    -- Hệ số tăng 8% cho chức danh Senior Engineer
        WHEN job_title = 'Engineer' THEN current_salary * 1.05    -- Hệ số tăng 5% cho chức danh Engineer
        ELSE current_salary    -- Giữ nguyên mức lương nếu không thuộc các chức danh trên
END;

-- 12. Viết truy vấn SQL để cập nhật số lượng của tất cả các sản phẩm trong cơ sở dữ liệu dựa trên số lượng hiện tại của chúng và số lượng đơn đặt hàng
-- đã được đặt cho sản phẩm đó, đặt số lượng mới bằng số lượng hiện tại trừ đi số lượng đơn đặt hàng đã đặt được đặt cho sản phẩm đó.
/* Không tìm được dữ liệu đơn đặt hàng và số lượng sản phẩm */

-- 13. Viết truy vấn SQL để cập nhật xếp hạng của tất cả các khách sạn trong cơ sở dữ liệu dựa trên xếp hạng hiện tại của họ và xếp hạng trung bình của 
-- tất cả các khách sạn trong cùng một thành phố, đặt xếp hạng mới bằng xếp hạng trung bình cộng với một hệ số phụ thuộc vào xếp hạng hiện tại đánh giá của khách sạn.
/* Đề bài là 1 database khác, không có dữ liệu db để xử lý */




CREATE TABLE customer (
  customer_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  store_id TINYINT UNSIGNED NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(50) DEFAULT NULL,
  address_id SMALLINT UNSIGNED NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  create_date DATETIME NOT NULL,
  last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (customer_id),
  KEY idx_fk_store_id (store_id),
  KEY idx_fk_address_id (address_id),
  KEY idx_last_name (last_name),
  CONSTRAINT fk_customer_address FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_customer_store FOREIGN KEY (store_id) REFERENCES store (store_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `film`
--

CREATE TABLE film (
  film_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(128) NOT NULL,
  description TEXT DEFAULT NULL,
  release_year YEAR DEFAULT NULL, -- nam phat hanh
  language_id TINYINT UNSIGNED NOT NULL, -- id ngon ngu
  original_language_id TINYINT UNSIGNED DEFAULT NULL, -- id ngon ngu goc
  rental_duration TINYINT UNSIGNED NOT NULL DEFAULT 3, -- thoi gian thue
  rental_rate DECIMAL(4,2) NOT NULL DEFAULT 4.99, -- gia thue
  length SMALLINT UNSIGNED DEFAULT NULL,
  replacement_cost DECIMAL(5,2) NOT NULL DEFAULT 19.99, -- gia thay the
  rating ENUM('G','PG','PG-13','R','NC-17') DEFAULT 'G',
  special_features SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (film_id),
  KEY idx_title (title),
  KEY idx_fk_language_id (language_id),
  KEY idx_fk_original_language_id (original_language_id),
  CONSTRAINT fk_film_language FOREIGN KEY (language_id) REFERENCES language (language_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_film_language_original FOREIGN KEY (original_language_id) REFERENCES language (language_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 2. Viết một truy vấn SQL để trả về tiêu đề của tất cả các bộ phim trong cơ sở dữ liệu, cùng với giá thuê và chi phí thay thế của chúng.
select title, rental_rate, replacement_cost from film;
-- 3. Viết truy vấn SQL để trả về 5 bộ phim được thuê nhiều nhất trong cơ sở dữ liệu, cùng với số lần chúng được thuê.
select title from film
where rental_duration > 5;
-- 4. Viết truy vấn SQL để trả về thời lượng thuê trung bình cho từng danh mục phim trong cơ sở dữ liệu.



--
-- Table structure for table `film_actor`
--

CREATE TABLE film_actor (
  actor_id SMALLINT UNSIGNED NOT NULL,
  film_id SMALLINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (actor_id,film_id),
  KEY idx_fk_film_id (`film_id`),
  CONSTRAINT fk_film_actor_actor FOREIGN KEY (actor_id) REFERENCES actor (actor_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_film_actor_film FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `film_category`
--

CREATE TABLE film_category (
  film_id SMALLINT UNSIGNED NOT NULL,
  category_id TINYINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (film_id, category_id),
  CONSTRAINT fk_film_category_film FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_film_category_category FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `film_text`
-- 
-- InnoDB added FULLTEXT support in 5.6.10. If you use an
-- earlier version, then consider upgrading (recommended) or 
-- changing InnoDB to MyISAM as the film_text engine
--

-- Use InnoDB for film_text as of 5.6.10, MyISAM prior to 5.6.10.
SET @old_default_storage_engine = @@default_storage_engine;
SET @@default_storage_engine = 'MyISAM';
/*!50610 SET @@default_storage_engine = 'InnoDB'*/;

CREATE TABLE film_text (
  film_id SMALLINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  PRIMARY KEY  (film_id),
  FULLTEXT KEY idx_title_description (title,description)
) DEFAULT CHARSET=utf8mb4;

SET @@default_storage_engine = @old_default_storage_engine;

--
-- Triggers for loading film_text from film
--

DELIMITER ;;
CREATE TRIGGER `ins_film` AFTER INSERT ON `film` FOR EACH ROW BEGIN
    INSERT INTO film_text (film_id, title, description)
        VALUES (new.film_id, new.title, new.description);
  END;;


CREATE TRIGGER `upd_film` AFTER UPDATE ON `film` FOR EACH ROW BEGIN
    IF (old.title != new.title) OR (old.description != new.description) OR (old.film_id != new.film_id)
    THEN
        UPDATE film_text
            SET title=new.title,
                description=new.description,
                film_id=new.film_id
        WHERE film_id=old.film_id;
    END IF;
  END;;


CREATE TRIGGER `del_film` AFTER DELETE ON `film` FOR EACH ROW BEGIN
    DELETE FROM film_text WHERE film_id = old.film_id;
  END;;

DELIMITER ;

--
-- Table structure for table `inventory`
--

CREATE TABLE inventory (
  inventory_id MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  film_id SMALLINT UNSIGNED NOT NULL,
  store_id TINYINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (inventory_id),
  KEY idx_fk_film_id (film_id),
  KEY idx_store_id_film_id (store_id,film_id),
  CONSTRAINT fk_inventory_store FOREIGN KEY (store_id) REFERENCES store (store_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_inventory_film FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `language`
--

CREATE TABLE language (
  language_id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name CHAR(20) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (language_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `payment`
--

CREATE TABLE payment (
  payment_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  customer_id SMALLINT UNSIGNED NOT NULL,
  staff_id TINYINT UNSIGNED NOT NULL,
  rental_id INT DEFAULT NULL,
  amount DECIMAL(5,2) NOT NULL,
  payment_date DATETIME NOT NULL,
  last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (payment_id),
  KEY idx_fk_staff_id (staff_id),
  KEY idx_fk_customer_id (customer_id),
  CONSTRAINT fk_payment_rental FOREIGN KEY (rental_id) REFERENCES rental (rental_id) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_payment_staff FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `rental`
--

CREATE TABLE rental (
  rental_id INT NOT NULL AUTO_INCREMENT,
  rental_date DATETIME NOT NULL,
  inventory_id MEDIUMINT UNSIGNED NOT NULL,
  customer_id SMALLINT UNSIGNED NOT NULL,
  return_date DATETIME DEFAULT NULL,
  staff_id TINYINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (rental_id),
  UNIQUE KEY  (rental_date,inventory_id,customer_id),
  KEY idx_fk_inventory_id (inventory_id),
  KEY idx_fk_customer_id (customer_id),
  KEY idx_fk_staff_id (staff_id),
  CONSTRAINT fk_rental_staff FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_rental_inventory FOREIGN KEY (inventory_id) REFERENCES inventory (inventory_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_rental_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `staff`
--

CREATE TABLE staff (
  staff_id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  address_id SMALLINT UNSIGNED NOT NULL,
  picture BLOB DEFAULT NULL,
  email VARCHAR(50) DEFAULT NULL,
  store_id TINYINT UNSIGNED NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  username VARCHAR(16) NOT NULL,
  password VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (staff_id),
  KEY idx_fk_store_id (store_id),
  KEY idx_fk_address_id (address_id),
  CONSTRAINT fk_staff_store FOREIGN KEY (store_id) REFERENCES store (store_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_staff_address FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `store`
--

CREATE TABLE store (
  store_id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  manager_staff_id TINYINT UNSIGNED NOT NULL,
  address_id SMALLINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (store_id),
  UNIQUE KEY idx_unique_manager (manager_staff_id),
  KEY idx_fk_address_id (address_id),
  CONSTRAINT fk_store_staff FOREIGN KEY (manager_staff_id) REFERENCES staff (staff_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_store_address FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- View structure for view `customer_list`
--

CREATE VIEW customer_list
AS
SELECT cu.customer_id AS ID, CONCAT(cu.first_name, _utf8mb4' ', cu.last_name) AS name, a.address AS address, a.postal_code AS `zip code`,
	a.phone AS phone, city.city AS city, country.country AS country, IF(cu.active, _utf8mb4'active',_utf8mb4'') AS notes, cu.store_id AS SID
FROM customer AS cu JOIN address AS a ON cu.address_id = a.address_id JOIN city ON a.city_id = city.city_id
	JOIN country ON city.country_id = country.country_id;

--
-- View structure for view `film_list`
--

CREATE VIEW film_list
AS
SELECT film.film_id AS FID, film.title AS title, film.description AS description, category.name AS category, film.rental_rate AS price,
	film.length AS length, film.rating AS rating, GROUP_CONCAT(CONCAT(actor.first_name, _utf8mb4' ', actor.last_name) SEPARATOR ', ') AS actors
FROM film LEFT JOIN film_category ON film_category.film_id = film.film_id
LEFT JOIN category ON category.category_id = film_category.category_id LEFT
JOIN film_actor ON film.film_id = film_actor.film_id LEFT JOIN actor ON
  film_actor.actor_id = actor.actor_id
GROUP BY film.film_id, category.name;

--
-- View structure for view `nicer_but_slower_film_list`
--

CREATE VIEW nicer_but_slower_film_list
AS
SELECT film.film_id AS FID, film.title AS title, film.description AS description, category.name AS category, film.rental_rate AS price,
	film.length AS length, film.rating AS rating, GROUP_CONCAT(CONCAT(CONCAT(UCASE(SUBSTR(actor.first_name,1,1)),
	LCASE(SUBSTR(actor.first_name,2,LENGTH(actor.first_name))),_utf8mb4' ',CONCAT(UCASE(SUBSTR(actor.last_name,1,1)),
	LCASE(SUBSTR(actor.last_name,2,LENGTH(actor.last_name)))))) SEPARATOR ', ') AS actors
FROM film LEFT JOIN film_category ON film_category.film_id = film.film_id
LEFT JOIN category ON category.category_id = film_category.category_id LEFT
JOIN film_actor ON film.film_id = film_actor.film_id LEFT JOIN actor ON
  film_actor.actor_id = actor.actor_id
GROUP BY film.film_id, category.name;

--
-- View structure for view `staff_list`
--

CREATE VIEW staff_list
AS
SELECT s.staff_id AS ID, CONCAT(s.first_name, _utf8mb4' ', s.last_name) AS name, a.address AS address, a.postal_code AS `zip code`, a.phone AS phone,
	city.city AS city, country.country AS country, s.store_id AS SID
FROM staff AS s JOIN address AS a ON s.address_id = a.address_id JOIN city ON a.city_id = city.city_id
	JOIN country ON city.country_id = country.country_id;

--
-- View structure for view `sales_by_store`
--

CREATE VIEW sales_by_store
AS
SELECT
CONCAT(c.city, _utf8mb4',', cy.country) AS store
, CONCAT(m.first_name, _utf8mb4' ', m.last_name) AS manager
, SUM(p.amount) AS total_sales
FROM payment AS p
INNER JOIN rental AS r ON p.rental_id = r.rental_id
INNER JOIN inventory AS i ON r.inventory_id = i.inventory_id
INNER JOIN store AS s ON i.store_id = s.store_id
INNER JOIN address AS a ON s.address_id = a.address_id
INNER JOIN city AS c ON a.city_id = c.city_id
INNER JOIN country AS cy ON c.country_id = cy.country_id
INNER JOIN staff AS m ON s.manager_staff_id = m.staff_id
GROUP BY s.store_id
ORDER BY cy.country, c.city;

--
-- View structure for view `sales_by_film_category`
--
-- Note that total sales will add up to >100% because
-- some titles belong to more than 1 category
--

CREATE VIEW sales_by_film_category
AS
SELECT
c.name AS category
, SUM(p.amount) AS total_sales
FROM payment AS p
INNER JOIN rental AS r ON p.rental_id = r.rental_id
INNER JOIN inventory AS i ON r.inventory_id = i.inventory_id
INNER JOIN film AS f ON i.film_id = f.film_id
INNER JOIN film_category AS fc ON f.film_id = fc.film_id
INNER JOIN category AS c ON fc.category_id = c.category_id
GROUP BY c.name
ORDER BY total_sales DESC;

--
-- View structure for view `actor_info`
--

CREATE DEFINER=CURRENT_USER SQL SECURITY INVOKER VIEW actor_info
AS
SELECT
a.actor_id,
a.first_name,
a.last_name,
GROUP_CONCAT(DISTINCT CONCAT(c.name, ': ',
		(SELECT GROUP_CONCAT(f.title ORDER BY f.title SEPARATOR ', ')
                    FROM sakila.film f
                    INNER JOIN sakila.film_category fc
                      ON f.film_id = fc.film_id
                    INNER JOIN sakila.film_actor fa
                      ON f.film_id = fa.film_id
                    WHERE fc.category_id = c.category_id
                    AND fa.actor_id = a.actor_id
                 )
             )
             ORDER BY c.name SEPARATOR '; ')
AS film_info
FROM sakila.actor a
LEFT JOIN sakila.film_actor fa
  ON a.actor_id = fa.actor_id
LEFT JOIN sakila.film_category fc
  ON fa.film_id = fc.film_id
LEFT JOIN sakila.category c
  ON fc.category_id = c.category_id
GROUP BY a.actor_id, a.first_name, a.last_name;

--
-- Procedure structure for procedure `rewards_report`
--

DELIMITER //

CREATE PROCEDURE rewards_report (
    IN min_monthly_purchases TINYINT UNSIGNED
    , IN min_dollar_amount_purchased DECIMAL(10,2)
    , OUT count_rewardees INT
)
LANGUAGE SQL
NOT DETERMINISTIC
READS SQL DATA
SQL SECURITY DEFINER
COMMENT 'Provides a customizable report on best customers'
proc: BEGIN

    DECLARE last_month_start DATE;
    DECLARE last_month_end DATE;

    /* Some sanity checks... */
    IF min_monthly_purchases = 0 THEN
        SELECT 'Minimum monthly purchases parameter must be > 0';
        LEAVE proc;
    END IF;
    IF min_dollar_amount_purchased = 0.00 THEN
        SELECT 'Minimum monthly dollar amount purchased parameter must be > $0.00';
        LEAVE proc;
    END IF;

    /* Determine start and end time periods */
    SET last_month_start = DATE_SUB(CURRENT_DATE(), INTERVAL 1 MONTH);
    SET last_month_start = STR_TO_DATE(CONCAT(YEAR(last_month_start),'-',MONTH(last_month_start),'-01'),'%Y-%m-%d');
    SET last_month_end = LAST_DAY(last_month_start);

    /*
        Create a temporary storage area for
        Customer IDs.
    */
    CREATE TEMPORARY TABLE tmpCustomer (customer_id SMALLINT UNSIGNED NOT NULL PRIMARY KEY);

    /*
        Find all customers meeting the
        monthly purchase requirements
    */
    INSERT INTO tmpCustomer (customer_id)
    SELECT p.customer_id
    FROM payment AS p
    WHERE DATE(p.payment_date) BETWEEN last_month_start AND last_month_end
    GROUP BY customer_id
    HAVING SUM(p.amount) > min_dollar_amount_purchased
    AND COUNT(customer_id) > min_monthly_purchases;

    /* Populate OUT parameter with count of found customers */
    SELECT COUNT(*) FROM tmpCustomer INTO count_rewardees;

    /*
        Output ALL customer information of matching rewardees.
        Customize output as needed.
    */
    SELECT c.*
    FROM tmpCustomer AS t
    INNER JOIN customer AS c ON t.customer_id = c.customer_id;

    /* Clean up */
    DROP TABLE tmpCustomer;
END //

DELIMITER ;

DELIMITER $$

CREATE FUNCTION get_customer_balance(p_customer_id INT, p_effective_date DATETIME) RETURNS DECIMAL(5,2)
    DETERMINISTIC
    READS SQL DATA
BEGIN

       #OK, WE NEED TO CALCULATE THE CURRENT BALANCE GIVEN A CUSTOMER_ID AND A DATE
       #THAT WE WANT THE BALANCE TO BE EFFECTIVE FOR. THE BALANCE IS:
       #   1) RENTAL FEES FOR ALL PREVIOUS RENTALS
       #   2) ONE DOLLAR FOR EVERY DAY THE PREVIOUS RENTALS ARE OVERDUE
       #   3) IF A FILM IS MORE THAN RENTAL_DURATION * 2 OVERDUE, CHARGE THE REPLACEMENT_COST
       #   4) SUBTRACT ALL PAYMENTS MADE BEFORE THE DATE SPECIFIED

  DECLARE v_rentfees DECIMAL(5,2); #FEES PAID TO RENT THE VIDEOS INITIALLY
  DECLARE v_overfees INTEGER;      #LATE FEES FOR PRIOR RENTALS
  DECLARE v_payments DECIMAL(5,2); #SUM OF PAYMENTS MADE PREVIOUSLY

  SELECT IFNULL(SUM(film.rental_rate),0) INTO v_rentfees
    FROM film, inventory, rental
    WHERE film.film_id = inventory.film_id
      AND inventory.inventory_id = rental.inventory_id
      AND rental.rental_date <= p_effective_date
      AND rental.customer_id = p_customer_id;

  SELECT IFNULL(SUM(IF((TO_DAYS(rental.return_date) - TO_DAYS(rental.rental_date)) > film.rental_duration,
        ((TO_DAYS(rental.return_date) - TO_DAYS(rental.rental_date)) - film.rental_duration),0)),0) INTO v_overfees
    FROM rental, inventory, film
    WHERE film.film_id = inventory.film_id
      AND inventory.inventory_id = rental.inventory_id
      AND rental.rental_date <= p_effective_date
      AND rental.customer_id = p_customer_id;


  SELECT IFNULL(SUM(payment.amount),0) INTO v_payments
    FROM payment

    WHERE payment.payment_date <= p_effective_date
    AND payment.customer_id = p_customer_id;

  RETURN v_rentfees + v_overfees - v_payments;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE film_in_stock(IN p_film_id INT, IN p_store_id INT, OUT p_film_count INT)
READS SQL DATA
BEGIN
     SELECT inventory_id
     FROM inventory
     WHERE film_id = p_film_id
     AND store_id = p_store_id
     AND inventory_in_stock(inventory_id);

     SELECT COUNT(*)
     FROM inventory
     WHERE film_id = p_film_id
     AND store_id = p_store_id
     AND inventory_in_stock(inventory_id)
     INTO p_film_count;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE film_not_in_stock(IN p_film_id INT, IN p_store_id INT, OUT p_film_count INT)
READS SQL DATA
BEGIN
     SELECT inventory_id
     FROM inventory
     WHERE film_id = p_film_id
     AND store_id = p_store_id
     AND NOT inventory_in_stock(inventory_id);

     SELECT COUNT(*)
     FROM inventory
     WHERE film_id = p_film_id
     AND store_id = p_store_id
     AND NOT inventory_in_stock(inventory_id)
     INTO p_film_count;
END $$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION inventory_held_by_customer(p_inventory_id INT) RETURNS INT
READS SQL DATA
BEGIN
  DECLARE v_customer_id INT;
  DECLARE EXIT HANDLER FOR NOT FOUND RETURN NULL;

  SELECT customer_id INTO v_customer_id
  FROM rental
  WHERE return_date IS NULL
  AND inventory_id = p_inventory_id;

  RETURN v_customer_id;
END $$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION inventory_in_stock(p_inventory_id INT) RETURNS BOOLEAN
READS SQL DATA
BEGIN
    DECLARE v_rentals INT;
    DECLARE v_out     INT;

    #AN ITEM IS IN-STOCK IF THERE ARE EITHER NO ROWS IN THE rental TABLE
    #FOR THE ITEM OR ALL ROWS HAVE return_date POPULATED

    SELECT COUNT(*) INTO v_rentals
    FROM rental
    WHERE inventory_id = p_inventory_id;

    IF v_rentals = 0 THEN
      RETURN TRUE;
    END IF;

    SELECT COUNT(rental_id) INTO v_out
    FROM inventory LEFT JOIN rental USING(inventory_id)
    WHERE inventory.inventory_id = p_inventory_id
    AND rental.return_date IS NULL;

    IF v_out > 0 THEN
      RETURN FALSE;
    ELSE
      RETURN TRUE;
    END IF;
END $$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


