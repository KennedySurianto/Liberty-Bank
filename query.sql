CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(20) NOT NULL UNIQUE,
	password_hash VARCHAR(255) NOT NULL,
	balance FLOAT NOT NULL CHECK (balance > 0)
);

CREATE TABLE transactions (
	transaction_id SERIAL PRIMARY KEY,
	sender_id INT NOT NULL REFERENCES users(user_id),
	receiver_id INT NOT NULL REFERENCES users(user_id),
	amount FLOAT NOT NULL,
	message TEXT
);