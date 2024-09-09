
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role SMALLINT NOT NULL
);

CREATE TABLE offices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    hourly_price DECIMAL(10, 2),
    daily_price DECIMAL(10, 2),
    monthly_price DECIMAL(10, 2),
    long_term_price DECIMAL(10, 2)
);

CREATE TABLE floors (
    id SERIAL PRIMARY KEY,
    svg_path VARCHAR(255) NOT NULL
);

CREATE TABLE availabilities (
    id SERIAL PRIMARY KEY,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    is_blocked BOOLEAN DEFAULT FALSE,
    office_id BIGINT,
    FOREIGN KEY (office_id) REFERENCES offices(id) ON DELETE CASCADE
);

CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status SMALLINT NOT NULL,
    rental_type VARCHAR(50) NOT NULL,
    total_price DECIMAL(10, 2),
    is_paid BOOLEAN DEFAULT FALSE,
    office_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (office_id) REFERENCES offices(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_date TIMESTAMP NOT NULL,
    is_successful BOOLEAN DEFAULT TRUE,
    booking_id BIGINT,
    FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE
);

CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    message VARCHAR(255) NOT NULL,
    sent_at TIMESTAMP NOT NULL,
    manager_id BIGINT,
    booking_id BIGINT,
    FOREIGN KEY (manager_id) REFERENCES users(id),
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);
