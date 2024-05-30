// package com.example.demo.mm;

// import com.example.demo.dto.LoginRequest;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.Map;
// import javax.validation.Valid;
// import lombok.RequiredArgsConstructor;
// import org.apache.http.protocol.HTTP;
// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
// // import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequiredArgsConstructor
// public class MmController {

//   // @Autowired
//   // MmRepository mmRepository;

//   // @RequestMapping(value = "/api/admin/get_all_users_mm", method = RequestMethod.GET)
//   @GetMapping("/api/login")
//   public ResponseEntity<Map<String, Object>> mockAkaApi(@RequestBody() LoginRequest lr) {
//     System.out.println("here is the request: " + lr.getEmail() + " : " + lr.getPassword());
//     // Simulate successful response
//     // String mockResponse =
//     //   "{\"responseCode\": 1000, \"status\": \"Success\", \"message\": \"Successfully Generated The Token.\", \"Token\": \"mock-token-value\"}";
//     Map<String, Object> res = new HashMap<>();
//     res.put("Desc", "Bad Credential!");
//     res.put("status", 1000);
//     // res.put("message", "Successfully Generated The Token");
//     res.put("Token", "mock-token-value");
//     return ResponseEntity.ok(res);
//   }
//   // 	@RequestMapping(value = "/api/admin/get_all_users_mm", method = RequestMethod.GET)
//   // 	public DataTablesOutput<Mm> expenses(@Valid DataTablesInput input) {

//   // 		return mmRepository.findAll(input);
//   // 	}

//   // 	@RequestMapping(value = "/api/admin/get_all_users_mmm", method = RequestMethod.POST)
//   // 	public DataTablesOutput<Mm> listPOST(@Valid @RequestBody DataTablesInput input) {
//   // 		System.out.println(input);
//   // 		System.out.println("here /api/admin/get_all_users_mm");

//   // //		System.out.println("length of input: " + input.getLength());

//   // 		DataTablesOutput<Mm> mm_out = mmRepository.findAll(input);
//   // 		System.out.println("here /api/admin/get_all_users_mm2");
//   // //		System.out.println("total records: " + mm_out.getRecordsTotal());
//   // //		System.out.println("total filtered: " + mm_out.getRecordsFiltered());
//   // 		System.out.println("data size: " + mm_out.getData().size());
//   // 		System.out.println("here /api/admin/get_all_users_mm3");
//   // //		for (int i = 0; i < mm_out.getData().size(); i++) {
//   // //			System.out.println("Name: " + mm_out.getData().get(i).getFirstName());
//   // //		}

//   // 		return mm_out;
//   // 	}

//   // 	@RequestMapping(value = "/api/admin/get_all_users_mm", method = RequestMethod.POST)
//   // 	public DataTablesOutput<Mm> listPOST(@Valid @RequestBody DataTablesInput input,
//   // 			@RequestParam Map<String, String> queryParameters) {
//   // 		Map<String, String> qp = new HashMap<>();
//   // 		qp.put("draw", input.getDraw().toString());
//   // 		System.out.println("Column count: " + input.getColumns().size());
//   // 		for(int i = 0; i< input.getColumns().size();) {
//   // 			int k = i;
//   // 			qp.put("columns[" + k + "].data", input.getColumns().get(i).getData());
//   // 			qp.put("columns[" + k + "].name", input.getColumns().get(i).getName());
//   // 			qp.put("columns[" + k + "].searchable", input.getColumns().get(i).getSearchable().toString());
//   // 			qp.put("columns[" + k + "].orderable", input.getColumns().get(i).getOrderable().toString());
//   // 			qp.put("columns[" + k + "].search.value", input.getColumns().get(i).getSearch().getValue().toString());
//   // 			qp.put("columns[" + k + "].search.regex",input.getColumns().get(i).getSearch().getRegex().toString());
//   // 			++i;
//   // 		}
//   // 		qp.put("order[0].column", input.getOrder().get(0).getColumn().toString());
//   // 		qp.put("order[0].dir", input.getOrder().get(0).getDir().toString());
//   // 		qp.put("start", input.getStart().toString());
//   // 		qp.put("length", input.getLength().toString());
//   // 		qp.put("search.value", input.getSearch().getValue());
//   // 		qp.put("search.regex", input.getSearch().getRegex().toString());
//   // 		qp.put("_", "1675537092447");

//   // 		System.out.println(":::\n" + qp);
//   // 		input.parseSearchPanesFromQueryParams(qp, Arrays.asList("firstName", "lastName"));

//   // 		DataTablesOutput<Mm> mm_out = mmRepository.findAll(input);
//   // 		System.out.println(mm_out);
//   // 		return mm_out;
//   // 	}

// }
