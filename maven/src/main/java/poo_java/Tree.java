package poo_java;

/**
 * Cette interface représente une structure d'arbre générique.
 */
public interface Tree<T> {

    /**
     * Ajoute un nœud avec la valeur spécifiée à l'arbre.
     *
     * @param i la valeur du nœud à ajouter
     */
    public void addNode(T i);

    /**
     * Supprime le nœud avec la valeur spécifiée de l'arbre.
     *
     * @param i la valeur du nœud à supprimer
     */
    public void removeNode(T i);

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
    public boolean exists(T i);

}
