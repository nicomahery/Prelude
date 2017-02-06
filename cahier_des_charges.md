

# Cahier des charges

# Introduction au problème posé

**Contexte et définition du problème**

Ce projet consiste à créer un site web dédié aux personnes souhaitant donner des biens à d&#39;autres personnes afin de s&#39;en libérer. La personne intéressée par le bien trouverait les  coordonnées du propriétaire et pourrait ensuite de fixer un RDV pour le récupérer.

Nous avons l&#39;exemple de lors d&#39;un déménagement, nous n&#39;avons pas forcément la place d&#39;emporter toutes nos affaires, il est donc possible avec ce site internet de donner les biens dont on n&#39;a plus besoin.

**Objectifs**

Réaliser un site web moderne et intuitif afin d&#39;avoir un maximum d&#39;utilisateurs pouvant alimenter le site. Le principe du site est de mettre en relation des particuliers qui souhaitent donner leur objets inutiles. Le site s&#39;organise par des annonces à afficher. Seul les utilisateurs ayant un compte et l&#39;administrateur peuvent ajouter, modifier et supprimer une annonce.

**Périmètre**

Notre site est accessible en France et DOM TOM. Les produits à données doivent se basés en France ou DOM TOM. Tout public peut s&#39;inscrire, se connecter, publier et consulter sur notre site. Notre site est adapté pour tout âge de personnes.



# Expression fonctionnelle du besoin

**Description fonctionnelle**

![cas_utilisation](https://raw.githubusercontent.com/nicomahery/projetGL/master/cas_utilisation.png)



**Fonction** : Inscription

**Objectif** : Permettre à l&#39;utilisateur de s&#39;inscrire pour pouvoir ensuite publier une annonce.

**Description** : Pour l&#39;inscription, les champs : nom, prénom, adresse, région, code postal, ville, mail, identifiant et mot de passe doivent apparaître.

Contrainte : Un utilisateur ne pourra pas s&#39;inscrire deux fois avec la même adresse mail ni avec le même identifiant.

**Fonction** : Connexion

**Objectif** : Permettre à l&#39;utilisateur de se connecter suite à l&#39;inscription pour pouvoir publier une annonce.

**Description** : Pour se connecter, on a besoin de l&#39;identifiant et du mot de passe.

**Fonction** : Poster une annonce

**Objectif** : Un utilisateur doit pouvoir poster des annonces.

**Description** : L&#39;utilisateur via un formulaire, peut poster une annonce en renseignant diverses informations (Adresse, titre de l&#39;annonce et une description)

**Fonction** : Consulter les annonces publiées

**Objectif** : Un visiteur peut consulter les annonces publiées sur le site sans être forcément inscrit.

**Description** : Dans la page de l&#39;annonce, on doit trouver, le titre de l&#39;annonce, la date de mise en ligne, le propriétaire de l&#39;annonce, son numéro de téléphone, son adresse, la description de l&#39;annonce et des photos s&#39;il y en a.

**Fonction** : Modifier une annonce

**Objectif** : Un utilisateur connecté est en droit de modifier sa propre annonce.

**Description** : Lorsque l&#39;utilisateur est connecté, il a accès à la liste de ses annonces et peut modifier l&#39;annonce grâce à un formulaire pré-rempli.

**Fonction** : Supprimer une annonce

**Objectif** : Un utilisateur connecté est en droit de supprimer ses annonces.

**Description** : Lorsque l&#39;utilisateur est connecté, il a accès à la liste de ses annonces et peut les supprimer à l&#39;aide d&#39;un bouton.

**Fonction** : Accéder aux informations de l&#39;annonceur

**Objectif** : Un utilisateur doit pouvoir contacter l&#39;auteur d&#39;une annonce afin de pouvoir concrétiser le don.

**Description** : Sur la page affichant une annonce, les informations de l&#39;annonceur seront disponible afin de pouvoir contacter ce dernier. L&#39;échange est organisé entre ces deux personnes.

**Fonction** : Recherche

**Objectif** : L&#39;utilisateur doit pouvoir faire des recherches par nom d&#39;annonce, par catégorie et par région.

**Description** : Pour la recherche par nom, l&#39;utilisateur doit pouvoir le saisir et faire rechercher. En ce qui concerne les catégories, il y a la liste de celles-ci qui s&#39;affiche afin de pouvoir en sélectionner une. Enfin, pour la recherche par région, l&#39;utilisateur peut sélectionner la région sur une carte et cela lui affiche les résultats.

**Fonction** : Modifier le profil d&#39;un utilisateur

**Objectif** : Un utilisateur connecté doit pouvoir modifier son profil.

**Descriptif** : Lorsque l&#39;utilisateur est connecté, il a accès à un formulaire pour pouvoir modifier son profil.

**Détails des fonctionnalités**

Notre site est composé de :

- --Un header menu avec un bouton permettant de rediriger vers la page d&#39;accueil, un autre pour rediriger sur voir toutes les annonces présentes sur le site et de rediriger vers la page des informations. Il y a aussi un bouton pour rediriger vers la page de connexion et d&#39;inscription
- --Un footer avec des liens vers les autres pages et les réseaux sociaux

Lorsqu&#39;on est sur le site, on arrive sur la page d&#39;accueil dans laquelle on trouve :

- --Des informations pour présenter le site et son principe
- --Un champs pour faire des recherches par type
- --Faire des recherches par catégories
- --Une carte pour faire des recherches par régions

Puis lorsque l&#39;on fait une recherche, on est redirigé vers une page affichant tous les résultats trouvés. On affiche donc les résultats avec la photo de l&#39;annonce, son titre, l&#39;auteur et la date de publication. On y trouve aussi la liste des catégories.

Ensuite, on peut sélectionner une annonce ce qui nous redirige vers la page contenant les détails de l&#39;annonce avec la possibilité d&#39;afficher plusieurs images, le titre, l&#39;auteur, l&#39;adresse, la description. On y trouve également une carte pour situer exactement l&#39;adresse de l&#39;annonce et un formulaire pour voir comment s&#39;y rendre.

Nous pouvons également se rendre sur la page inscription et connexion. Sur cette page se trouve deux formulaires. Celui de connexion comporte deux champs : &quot;Nom d&#39;utilisateur&quot; et &quot;Mot de passe&quot; et celui d&#39;inscription qui comporte plusieurs champs : le nom, l&#39;adresse, le login  et le mot de passe,  Suite à la connexion et à l&#39;inscription on est redirigé vers la page d&#39;accueil de connexion dans laquelle on a trois boutons principaux : &quot;Consulter ces annonces&quot;, &quot;Ajouter une annonce&quot; et &quot;Modifier son profil&quot;.

Dans la page consulter ces annonces, on a la liste des annonces et des boutons pour modifier ou supprimer l&#39;annonce. Si on clique sur modifier, cela renvoie sur un formulaire pré-rempli avec les informations de l&#39;annonce et on peut alors les modifier. Si on clique sur supprimer cela supprime l&#39;annonce de la base de données.

Dans la page ajouter une annonce, nous trouvons un formulaire avec les champs sur le nom du produit, la catégorie, ...

Enfin, nous pouvons également modifier le profil de l&#39;utilisateur connecté avec un formulaire pré-rempli avec les informations de base de l&#39;utilisateur.

# Solution proposée pour répondre au besoin

Le projet final comporte toutes ces fonctionnalités. Le plus important est de tout d&#39;abord créer les pages visibles par un visiteur, c&#39;est-à-dire, la page d&#39;accueil qui nous permettra de faire des recherches et de présenter le site, la page de recherche qui affiche les produits sélectionnés par la recherche et enfin la page produit en elle-même avec le descriptif de celui-ci.

Puis, il faut pouvoir ajouter des annonces. Pour cela, il faut une connexion puis des formulaires permettant d&#39;ajouter, modifier et supprimer ces annonces.

Ensuite, le visiteur doit être en mesure de faire des recherches par noms, par catégories et par régions. Il faut donc implémenter ces fonctionnalités.

Enfin, la dernière fonctionnalité est de pouvoir modifier les informations d&#39;un utilisateur au cas où l&#39;utilisateur déménage par exemple, celui-ci ne sera pas obligé de se créer un nouveau compte.

Conception

Base de données

Pour enregistrer les annonces, permettre l&#39;inscription et faire des recherches, il nous faut une base de données.
