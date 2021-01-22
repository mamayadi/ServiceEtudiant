<?php

$mysqli = new mysqli("localhost", "root", "", "serviceetudiant");

/* Vérification de la connexion */
if (mysqli_connect_errno()) {
printf("Échec de la connexion : %s\n", mysqli_connect_error());
exit();
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
header("Content-Type: application/json; charset=UTF-8");
$obj = json_decode(file_get_contents('php://input'), true);
$login=$obj['login'];
$passwd=$obj['passwd'];
$stmt = $mysqli->prepare("SELECT `id`, `iu`, `nom`, `prenom`, `id_etablissement`, `login`, `autorisation` FROM user where login='$login' and passwd='$passwd' Limit 1;");
$stmt->execute();
$result = $stmt->get_result();
$outp = $result->fetch_all(MYSQLI_ASSOC);
if(!empty($outp)){
echo json_encode($outp[0]);
} else {
http_response_code(404);
$object->message = "User not found";
echo json_encode($object);
}
$mysqli->close();
}
?>