<?php

$mysqli = new mysqli("localhost", "root", "", "serviceetudiant");

/* Vérification de la connexion */
if (mysqli_connect_errno()) {
printf("Échec de la connexion : %s\n", mysqli_connect_error());
exit();
}

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
header("Content-Type: application/json; charset=UTF-8");
// $obj = json_decode(file_get_contents('php://input'), true);
$stmt = $mysqli->prepare("SELECT `id`, `libelle`, `adresse` FROM etablissement;");
$stmt->execute();
$result = $stmt->get_result();
$outp = $result->fetch_all(MYSQLI_ASSOC);

echo json_encode($outp);
$mysqli->close();
}
?>