package by.it_akademy.fitness.odto;

public class OutputComDishDTO {

    private OutputProductDTO product;
    private double weight;

    public OutputComDishDTO() {
    }

    public OutputComDishDTO(OutputProductDTO product, double weight) {
        this.product = product;
        this.weight = weight;
    }

    public OutputProductDTO getProduct() {
        return product;
    }

    public void setProduct(OutputProductDTO product) {
        this.product = product;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
