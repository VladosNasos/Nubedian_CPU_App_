-- seed sockets
INSERT INTO socket (name) VALUES
  ('AM4'),
  ('AM5'),
  ('LGA1151'),
  ('LGA1700'),
  ('TR4')
ON DUPLICATE KEY UPDATE name = name;
