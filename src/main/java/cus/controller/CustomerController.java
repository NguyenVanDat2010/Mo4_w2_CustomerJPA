package cus.controller;

import cus.model.Customer;
import cus.service.impl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showCustomerList(){
        ModelAndView modelAndView = new ModelAndView("customer/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/customers/create")
    public ModelAndView showCustomerCreate(){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/customers/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("success","New customer created successfully!");
        return modelAndView;
    }

    @GetMapping("/customers/edit/{id}")
    public ModelAndView showCustomerEdit(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView;
        if(customer != null) {
            modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);

        }else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/customers/edit")
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        redirectAttributes.addFlashAttribute("success","Customer was updated successfully!");
        return modelAndView;
    }

    @GetMapping("/customers/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id,RedirectAttributes redirectAttributes){
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("success","Customer was deleted successfully!");
        return new ModelAndView("redirect:/customers");
    }
}
