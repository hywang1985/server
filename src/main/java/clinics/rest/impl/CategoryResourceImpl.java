package clinics.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import clinics.business.services.CategoryService;
import clinics.model.DepartmentModel;

@RestController
@RequestMapping(value = "/categories", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CategoryResourceImpl {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DepartmentModel save(@RequestBody DepartmentModel model) {
		return categoryService.save(model);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public DepartmentModel getById(@PathVariable("id") int id) {
		return categoryService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> removeById(@PathVariable("id") int id) throws Exception {
		DepartmentModel category = categoryService.getById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == category) {
			headers.add("errorMessage", "Category with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				categoryService.removeById(id);
				headers.add("errorMessage", "Category with ID " + id + " Deleted Successfully");
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Category with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<DepartmentModel> getAll() {
		return categoryService.getAll();
	}
}
