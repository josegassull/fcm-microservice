CREATE TABLE registered_user (
  id SERIAL PRIMARY KEY,
  external_id INTEGER NOT NULL, -- backoffice user id
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE device_type AS ENUM ('Android', 'iOS', 'other');

CREATE TABLE device (
  id SERIAL PRIMARY KEY,
  uuid VARCHAR(255) NOT NULL,
  type device_type DEFAULT 'other',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fcm_token (
  id SERIAL PRIMARY KEY,
  token VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  device_id INTEGER REFERENCES device(id) ON DELETE CASCADE, -- foreign key linking to device
  UNIQUE(device_id) -- ensure only 1 fcm token for device
);

-- many to many relationship between user-device
CREATE TABLE user_device (
  id SERIAL PRIMARY KEY,
  registered_user_id INTEGER REFERENCES registered_user(id) ON DELETE CASCADE,
  device_id INTEGER REFERENCES device(id) ON DELETE CASCADE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(registered_user_id, device_id) -- each registered_user-device pair is unique
);

-- to speed up lookups on foreign keys
CREATE INDEX idx_user_device_registered_user_id ON user_device(registered_user_id);
CREATE INDEX idx_user_device_device_id ON user_device(device_id);
