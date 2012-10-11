<?php
$con=mysql_connect("localhost", "root", "") or die(mysql_error());
mysql_select_db("db_lab") or die(mysql_error());

$uname = $_GET['uname'];
$pwd = $_GET['pwd'];
$pwd = md5($pwd);
$query = mysql_query("SELECT count(*) from user_details where uid = '".$uname."' AND pwd = '".$pwd."'")or die("MySQL ERROR: ".mysql_error());
$row = mysql_fetch_array($query);
echo $row[0];
?>
