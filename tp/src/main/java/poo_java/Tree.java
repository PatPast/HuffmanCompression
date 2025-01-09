
/**
 * Cette interface représente une structure d'arbre générique.
 */
public interface Tree {

    /**
     * Ajoute un nœud avec la valeur spécifiée à l'arbre.
     *
     * @param i la valeur du nœud à ajouter
     */
    public void addNode(Object i);

    /**
     * Supprime le nœud avec la valeur spécifiée de l'arbre.
     *
     * @param i la valeur du nœud à supprimer
     */
    public void removeNode(Object i);

    /**
     * Retourne la hauteur de l'arbre.
     *
     * @return la hauteur de l'arbre
     */
    public int getHeight();

    /**
     * Vérifie si un nœud avec la valeur spécifiée existe dans l'arbre.
     *
     * @param i la valeur à vérifier
     * @return true si un nœud avec la valeur spécifiée existe, false sinon
     */
    public boolean exists(Object i);

    public void setLeft(BinaryTree node);

    public void setRight(BinaryTree node);

}
