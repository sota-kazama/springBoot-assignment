INSERT INTO ARTISTS(ARTIST_NAME, ARTIST_HIRAGANA_NAME, ARTIST_ART_URL) VALUES
('乃木坂46','のぎざかふぉーてぃしっくす','https://nogizaka46.com'),
('日向坂46','ひなたざかふぉーてぃしっくす','https://hinatazaka46.com'),
('櫻坂46','さくらざかふぉーてぃしっくす','https://sakurazaka46.com'),
('ももいろクローバーZ','ももいろくろーばーぜっと','https://www.momocho.net'),
('CANDY TUNE','きゃんでぃーちゅーん','https://candytune.asobisystem.com/');

-- 乃木坂46のメンバー3人
INSERT INTO MEMBERS(MEMBER_NAME, MEMBER_HIRAGANA_NAME, MEMBER_BIRTHDAY, MEMBER_PHOTO,ARTIST_ID) VALUES
('五百城茉央', 'いおきまお', '2005-07-29', '',1),
('池田瑛紗', 'いけだてれさ', '2002-05-12', '',1),
('一ノ瀬美空', 'いちのせみく', '2003-05-24', '',1);

-- 日向坂46
INSERT INTO MEMBERS(MEMBER_NAME, MEMBER_HIRAGANA_NAME, MEMBER_BIRTHDAY, MEMBER_PHOTO,ARTIST_ID) VALUES
('松田好花', 'まつだこのか', '1999-04-27', '', 2);

-- 櫻坂46
INSERT INTO MEMBERS(MEMBER_NAME, MEMBER_HIRAGANA_NAME, MEMBER_BIRTHDAY, MEMBER_PHOTO,ARTIST_ID) VALUES
('山崎天', 'やまさきてん', '2006-09-28', '', 3);

-- ももクロ
INSERT INTO MEMBERS(MEMBER_NAME, MEMBER_HIRAGANA_NAME, MEMBER_BIRTHDAY, MEMBER_PHOTO,ARTIST_ID) VALUES
('玉井詩織', 'たまいしおり', '1995-06-04', '', 4);

-- きゃんちゅー
INSERT INTO MEMBERS(MEMBER_NAME, MEMBER_HIRAGANA_NAME, MEMBER_BIRTHDAY, MEMBER_PHOTO,ARTIST_ID) VALUES
('村川緋杏', 'むらかわびびあん', '1999-12-03', '', 5),
('福山梨乃', 'ふくやまりの', '1997-12-24', '', 5),
('小川奈々子', 'おがわななこ', '1999-10-01', '', 5),
('南なつ', 'みなみなつ', '2001-03-27', '', 5),
('立花琴未', 'たちばなことみ', '2002-05-25', '', 5),
('宮野静', 'みやのしずか', '2002-05-30', '', 5),
('桐原美月', 'きりはらみづき', '2003-02-21', '', 5);