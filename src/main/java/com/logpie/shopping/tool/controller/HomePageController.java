package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.annotation.LogEnvironment;
import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.AddressService;
import com.logpie.shopping.tool.service.AdminService;
import com.logpie.shopping.tool.service.BrandService;
import com.logpie.shopping.tool.service.ClientService;
import com.logpie.shopping.tool.service.ColorService;
import com.logpie.shopping.tool.service.OrderService;
import com.logpie.shopping.tool.service.PackageService;
import com.logpie.shopping.tool.service.ProductService;
import com.logpie.shopping.tool.service.ShopService;
import com.logpie.shopping.tool.service.SubCategoryService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private PackageService packageService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SubCategoryService subcategoryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@ModelAttribute
	public void preHandle() {
		logger.trace("pre handle started...");

	}

	@RequestMapping(path = "/testing/shop", method = RequestMethod.GET)
	public String testShop(final Model model) {
		logger.trace("Request started...");

		Shop ashop = shopService.getShopById(4L);
		logger.debug("get shop - " + shopService.getShopById(4L).getPath());

		ashop.setName("update_shop_0424");
		shopService.updateShop(ashop);

		Shop shop = shopService.getShopByPath("logpie");
		logger.debug("get shop id- " + shop.getId());

		logger.trace("Request done...");

		model.addAttribute("name", shop.getName());
		return "greeting";
	}

	@RequestMapping(path = "/testing/client", method = RequestMethod.GET)
	public String testClient(final Model model) {
		logger.trace("Request started...");

		Shop shop = shopService.getShopById(1L);
		logger.debug("get shop - " + shop.getPath());

		// Client client = new Client(null, null, "test_client_0425", null,
		// null, null, null, null, null, shop);
		// Long id = clientService.createClient(client);

		// client = clientService.getClientById(id);
		// client.setWechatName("wechat_name_0425");
		// clientService.updateClient(client);

		Page<Client> page = clientService.getClientsByShopId(2, 1L);
		for (Client c : page) {
			logger.debug("client id: " + c.getId());
		}
		logger.trace("Request done...");

		model.addAttribute("name", "world");
		return "greeting";
	}

	@RequestMapping(path = "/testing/address", method = RequestMethod.GET)
	public String testAddress(final Model model) {
		logger.trace("Request started...");

		Shop shop = shopService.getShopById(1L);
		logger.debug("get shop - " + shop.getPath());
		Client client = clientService.getClientById(1L);

		Address address = new Address(null, "test_address_0426",
				"test_address_name_0426", "test_address_phone_0426", null,
				client, shop);
		addressService.createAddress(address);

		Page<Address> page = addressService.getAddressesByShopId(1, 1L);
		Page<Address> page2 = addressService.getAddressesByShopId(2, 1L);
		for (Address a : page) {
			logger.debug("No.1 - addr id: " + a.getId());
		}
		for (Address a : page2) {
			logger.debug("No.2 - addr id: " + a.getId());
		}

		Page<Address> page3 = addressService.getAddressesByClientId(1, 1L);
		for (Address a : page3) {
			logger.debug("addr id: " + a.getId());
		}
		logger.trace("Request done...");

		model.addAttribute("name", "world");
		return "greeting";
	}

	@RequestMapping(path = "/testing/product", method = RequestMethod.GET)
	public String testProduct(final Model model) {
		logger.trace("Request started...");

		Shop shop = shopService.getShopById(1L);
		logger.debug("get shop - " + shop.getPath());

		// Product product = productService.getProductById(3L);
		// productService.updateProduct(product);

		Page<Product> page = productService.getProductsByShopId(1, 1L);
		Page<Product> page2 = productService.getProductsByShopId(2, 2L);
		for (Product p : page) {
			logger.debug("No.1 - pro id: " + p.getId());
		}
		for (Product p : page2) {
			logger.debug("No.2 - pro id: " + p.getId());
		}

		Page<Product> page3 = productService.getProductsByBrandId(1, 1L);
		for (Product p : page3) {
			logger.debug("Brand - pro id: " + p.getId());
		}
		logger.trace("Request done...");

		model.addAttribute("name", "world");
		return "greeting";
	}
}
