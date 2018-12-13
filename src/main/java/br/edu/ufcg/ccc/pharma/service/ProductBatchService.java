package br.edu.ufcg.ccc.pharma.service;

import br.edu.ufcg.ccc.pharma.model.Batch;
import br.edu.ufcg.ccc.pharma.model.Product;
import br.edu.ufcg.ccc.pharma.repository.BatchRepository;
import br.edu.ufcg.ccc.pharma.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductBatchService {
    private final ProductRepository productDAO;
    private final BatchRepository batchDAO;

    @Autowired
    public ProductBatchService(ProductRepository productDAO, BatchRepository batchDAO) {
        this.productDAO = productDAO;
        this.batchDAO = batchDAO;
    }

    public Batch saveBatch(Batch batch) {
        setupAvailability(batch);
        Product product = saveProduct(batch.getProduct());
        batch.setProduct(product);
        return batchDAO.save(batch);
    }

    private void setupAvailability(Batch batch) {
        Optional<Product> productOptional = productDAO.findById(batch.getProduct().getId());

        if (productOptional.isPresent()) {
            Product productInDB = productOptional.get();

            if (!productInDB.isAvailable()) {
                if (batch.getExpiration().after(new Date()) && batch.getAmount() > 0)
                    batch.getProduct().setAvailable(true);
                else
                    batch.getProduct().setAvailable(false);
            }
        } else {
            if (batch.getExpiration().after(new Date()) && batch.getAmount() > 0)
                batch.getProduct().setAvailable(true);
            else
                batch.getProduct().setAvailable(false);
        }
    }

    public int countStock(long id) {
        int amount = -1;
        if (productDAO.existsById(id))
            amount = batchDAO.findTotalAmount(id);
        return amount;
    }

    public List<Batch> findAllBatches() {
        return (List<Batch>) batchDAO.findAll();
    }

    public Batch findBatchById(long id) {
        Batch batch = null;
        Optional<Batch> optional = batchDAO.findById(id);
        if (optional.isPresent())
            batch = optional.get();
        return batch;
    }

    public void deleteBatch(long id) {
        batchDAO.deleteById(id);
    }

    public Product saveProduct(Product product) { return productDAO.save(product); }
    public void deleteProduct(long id) { productDAO.deleteById(id);}
    public Product findProductById(long id) {
        Product product = null;
        Optional<Product> optional = productDAO.findById(id);
        if (optional.isPresent())
            product = optional.get();
        return product;
    }
    public List<Product> findAllProducts() { return (List<Product>) productDAO.findAll(); }
}
