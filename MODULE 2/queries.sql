-- User Upcoming Events
SELECT u.full_name, e.title, e.city, e.start_date
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' AND u.city = e.city
ORDER BY e.start_date;


-- Top Rated Events (min 10 feedbacks)
SELECT e.title, AVG(f.rating) AS average_rating, COUNT(f.feedback_id) AS total_feedbacks
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.event_id
HAVING COUNT(f.feedback_id) >= 10
ORDER BY average_rating DESC;


-- Inactive Users (no registration in last 90 days)
SELECT u.user_id, u.full_name, u.email
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.user_id
HAVING MAX(r.registration_date) IS NULL OR MAX(r.registration_date) < CURDATE() - INTERVAL 90 DAY;


-- Peak Session Hours (10 AM to 12 PM)
SELECT e.title AS event_title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
WHERE TIME(s.start_time) BETWEEN '10:00:00' AND '11:59:59'
GROUP BY e.event_id;


-- Most Active Cities (Top 5 by unique users)
SELECT u.city, COUNT(DISTINCT r.user_id) AS total_users
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY total_users DESC
LIMIT 5;


-- Event Resource Summary
SELECT e.title, 
  SUM(resource_type = 'pdf') AS pdf_count,
  SUM(resource_type = 'image') AS image_count,
  SUM(resource_type = 'link') AS link_count
FROM Events e
JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id;


-- Low Feedback Alerts (< 3 rating)
SELECT u.full_name, e.title AS event_title, f.rating, f.comments
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;


-- Sessions per Upcoming Event
SELECT e.title AS event_title, COUNT(s.session_id) AS session_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id;


-- Organizer Event Summary
SELECT u.full_name AS organizer, 
       e.status, 
       COUNT(e.event_id) AS event_count
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, e.status;


-- Feedback Gap (events with registrations but no feedback)
SELECT e.title
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE f.feedback_id IS NULL
GROUP BY e.event_id;

-- Daily New User Count (Last 7 days)
SELECT registration_date, COUNT(*) AS new_users
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date
ORDER BY registration_date DESC;


-- Event with Maximum Sessions
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id
HAVING COUNT(s.session_id) = (
  SELECT MAX(session_total) FROM (
    SELECT COUNT(*) AS session_total
    FROM Sessions
    GROUP BY event_id
  ) AS session_counts
);


-- Average Rating per City
SELECT e.city, AVG(f.rating) AS avg_rating
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.city;


-- Most Registered Events (Top 3)
SELECT e.title, COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id
ORDER BY total_registrations DESC
LIMIT 3;


-- Event Session Time Conflict (within same event)
SELECT s1.event_id, s1.title AS session1, s2.title AS session2
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id AND s1.session_id <> s2.session_id
WHERE s1.start_time < s2.end_time AND s1.end_time > s2.start_time;


-- Unregistered Active Users (last 30 days)
SELECT u.user_id, u.full_name
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
WHERE u.registration_date >= CURDATE() - INTERVAL 30 DAY
  AND r.registration_id IS NULL;


-- Multi-Session Speakers
SELECT speaker_name, COUNT(*) AS session_count
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(*) > 1;


-- Resource Availability Check (events with no resources)
SELECT e.event_id, e.title
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
WHERE r.resource_id IS NULL;


-- Completed Events with Feedback Summary
SELECT e.title,
       COUNT(DISTINCT r.registration_id) AS total_registrations,
       ROUND(AVG(f.rating), 2) AS avg_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id;


-- User Engagement Index
SELECT u.full_name,
       COUNT(DISTINCT r.event_id) AS events_attended,
       COUNT(DISTINCT f.feedback_id) AS feedbacks_given
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id;


-- Top Feedback Providers (Top 5 users)
SELECT u.full_name, COUNT(f.feedback_id) AS total_feedbacks
FROM Users u
JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id
ORDER BY total_feedbacks DESC
LIMIT 5;


-- Duplicate Registrations Check
SELECT user_id, event_id, COUNT(*) AS duplicate_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(*) > 1;


-- Registration Trends (Last 12 Months)
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS month,
       COUNT(*) AS total_registrations
FROM Registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY month
ORDER BY month;


-- Average Session Duration per Event (in minutes)
SELECT e.title,
       ROUND(AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)), 2) AS avg_duration_mins
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id;


-- Events Without Sessions
SELECT e.event_id, e.title
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE s.session_id IS NULL;
