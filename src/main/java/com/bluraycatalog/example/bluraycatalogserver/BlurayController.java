package com.bluraycatalog.example.bluraycatalogserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class BlurayController {
    @Autowired
    private BlurayRepository blurayRepository;

    @GetMapping("/blurays")
    public List<Bluray>  getAllBlurays(){
        return blurayRepository.findAll();
    }

    @GetMapping("/blurays/{id}")
    public Bluray getBluray(@PathVariable Long id) throws BlurayNotFoundException{
        Optional<Bluray> bluray = blurayRepository.findById(id);

        if(!bluray.isPresent()){
            throw new BlurayNotFoundException("id: " + id );
        }

        return bluray.get();
    }

    @PostMapping("/blurays")
    public ResponseEntity<Object> createBluray(@RequestBody Bluray bluray){
        Bluray savedBluray = blurayRepository.save(bluray);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBluray.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/blurays/{id}")
    public void deleteBluray(@PathVariable long id) {
        blurayRepository.deleteById(id);
    }

}
