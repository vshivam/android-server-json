<?php
$con=mysql_connect("localhost", "root", "") or die(mysql_error());
mysql_select_db("db_lab") or die(mysql_error());

$cat = $_GET['cat'];

$query = mysql_query("SELECT event_name from events where cat = '".$cat."'")or die("MySQL ERROR: ".mysql_error());
$row = mysql_fetch_array($query);
echo json_encode($row);
?>