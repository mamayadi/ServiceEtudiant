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
$idService = $obj['id_service'];
$idUser = $obj['id_user'];
$dateDemande = $obj['date_demande'];
$etatDemande = $obj['etat_demande'];
$stmt = $mysqli->prepare("INSERT INTO service_user (`id_service`, `id_user`, `date_demande`, `etat_demande`) VALUES ('$idService', '$idUser', '$dateDemande', '$etatDemande');");
$stmt->execute();
echo json_encode($obj);
$mysqli->close();
} else  if ($_SERVER['REQUEST_METHOD'] == 'GET' && isset($_GET['id'])) {
// header("Content-Type: application/json; charset=UTF-8");
// $obj = json_decode(file_get_contents('php://input'), true);
$id=$_GET["id"];
// var_dump($id); die;
$stmt = $mysqli->prepare("SELECT `id`, `id_service`, `id_user`, `date_demande`, `etat_demande` FROM service_user where id_user='$id';");
$stmt->execute();
$result = $stmt->get_result();
$outp = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($outp);
$mysqli->close();
} else {
// header("Content-Type: application/json; charset=UTF-8");
// $obj = json_decode(file_get_contents('php://input'), true);
$stmt = $mysqli->prepare("SELECT `id`, `id_service`, `id_user`, `date_demande`, `etat_demande` FROM service_user;");
$stmt->execute();
$result = $stmt->get_result();
$outp = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($outp);
$mysqli->close();
}
?>