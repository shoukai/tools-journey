package org.apframework.webflux.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/city")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<City> findOneCity(@PathVariable("id") Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.findCityById(id)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Flux<City> findAllCity() {
        return Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> {
                        System.out.println("for 1:" + Thread.currentThread().getName());
                        cityFluxSink.next(city);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        });
    }

    @RequestMapping(value = "/reactor", method = RequestMethod.GET)
    public Flux<City> findAllCityReactorTest() {
        System.out.println("root begin:" + Thread.currentThread().getName());
        Flux<City> cityFlux = Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> {
                        System.out.println("for 1:" + Thread.currentThread().getName());
                        cityFluxSink.next(city);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
            cityFluxSink.complete();
        });
        System.out.println("root middle:" + Thread.currentThread().getName());
        Flux<City> cityFlux2 = Flux.create(cityFluxSink -> {
            cityService.findAllCity().forEach(city -> {
                        System.out.println("for 2:" + Thread.currentThread().getName());
                        cityFluxSink.next(city);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
            cityFluxSink.complete();
        });
        System.out.println("root end:" + Thread.currentThread().getName());
        return cityFlux.concatWith(cityFlux2);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Mono<Long> createCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.saveCity(city)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Mono<Long> modifyCity(@RequestBody City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.updateCity(city)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityService.deleteCity(id)));
    }
}