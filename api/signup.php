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

$iu = $obj['iu'];
$nom = $obj['nom'];
$prenom = $obj['prenom'];
$idEtablissement = $obj['id_etablissement'];
$login = $obj['login'];
$passwd = $obj['passwd'];
$autorisation = $obj['autorisation'];
//  var_dump($obj['iu']);die;
$stmt = $mysqli->prepare("INSERT INTO user (`iu`, `nom`, `prenom`, `id_etablissement`, `login`, `passwd`, `autorisation`) VALUES ('$iu' , '$nom', '$prenom', '$idEtablissement' , '$login' , '$passwd', '$autorisation');");
$stmt->execute();
// $result = $stmt->get_result();
// $outp = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($obj);
$stmt->close();
$mysqli->close();
}
?>
