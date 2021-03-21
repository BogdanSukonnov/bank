package io.github.bogdansukonnov.bank.controller;

import io.github.bogdansukonnov.bank.dto.NewOperationDto;
import io.github.bogdansukonnov.bank.dto.OperationDto;
import io.github.bogdansukonnov.bank.service.OperationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RequestMapping("api/operations")
public class OperationController {

    @NonNull
    private final OperationService operationService;

    @GetMapping(value = "/{accountId}", produces = "application/json")
    @ApiOperation("Get all operations of given user account")
    public List<OperationDto> accounts(@ApiParam("account id") @PathVariable("accountId") UUID accountId
            , HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), accountId);
        return operationService.accountOperations(accountId);
    }

    @GetMapping(value = "/{accountId}/{operationId}", produces = "application/json")
    @ApiOperation("Get one operation")
    public OperationDto operation(@ApiParam("account id") @PathVariable("accountId") UUID accountId
                              ,@ApiParam("operation id") @PathVariable UUID operationId
            , HttpServletRequest request) {
        log.debug("{} - {} - {}", request.getRequestURI(), accountId, operationId);
        return operationService.operation(accountId, operationId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Save new operation")
    public OperationDto addOperation(@ApiParam("new operation") @Valid @RequestBody NewOperationDto newOperationDto
            , HttpServletRequest request) {
        log.debug("{} - {}", request.getRequestURI(), newOperationDto);
        return operationService.addOperation(newOperationDto);
    }

    @DeleteMapping(value = "/{accountId}/{operationId}", produces = "application/json")
    @ApiOperation("Remove operation")
    public void deleteOperation(@ApiParam("user id") @PathVariable UUID accountId,
                              @ApiParam("operation id") @PathVariable UUID operationId,
                              HttpServletRequest request) {
        log.debug("{} - {} - {}", request.getRequestURI(), accountId, operationId);
        operationService.deleteOperation(accountId, operationId);
    }
}
