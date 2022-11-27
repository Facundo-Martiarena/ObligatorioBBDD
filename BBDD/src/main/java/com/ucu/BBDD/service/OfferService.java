package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.model.*;
import com.ucu.BBDD.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Offer> getOffers(){
        return offerRepository.findAll();
    }

    /*public List<OfferResponseDTO> getOffers(String email){
       return new ArrayList<>();
    }*/

    public Offer createOffer(String email, Integer publication_id){

        String sql = String.format("INSERT INTO public.offer(" +
                " acepted_date, email_bidder, publication_id, state)" +
                " SELECT (SELECT NOW()::timestamp), '%s', %s, pub.state_damage" +
                " FROM public.publication as pub" +
                " WHERE pub.publication_id = %s;",email,publication_id, publication_id);

        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> new Offer(

                rs.getInt("id_offer"),
                rs.getString("state"),
                rs.getDate("acepted_date"),
                rs.getString("email_bidder"),
                rs.getInt("publication_id")
        ));
    }

    public String deleteOffer(String idOffer){
        offerRepository.deleteById(idOffer);
        return "Offer removed";
    }

//    public OfferResponseList getOffersBidder(String email){
//
//        String sql = String.format("SELECT pub.email as email_publisher, o.email_bidder as email_bidder, fuo.number_f_offer_bidder, f1.description as description_bidder, f2.description as description_publisher, o.state" +
//                " FROM public.figure as f1,public.figure as f2, public.offer as o, public.publication as pub," +
//                " public.figure_user_offer as fuo" +
//                " WHERE o.email_bidder = '%s' AND fuo.id_offer = o.id_offer" +
//                " AND f1.number = fuo.number_f_offer_bidder AND o.publication_id = pub.publication_id" +
//                " AND pub.number_f = f2.number;",email);
//        List<OfferResponsePrimary> offerBidderList = jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponsePrimary(
//                rs.getString("description_bidder"),
//                rs.getString("description_publisher"),
//                rs.getString("state")
//
//        ));
//
//        List<String> descriptions = new ArrayList<>();
//        OfferResponseList offerlist = new OfferResponseList();
//        List<OffersResponse> offers = new ArrayList<>();
//        offerBidderList.stream().forEach(offerB -> descriptions.add(offerB.getDescription_publisher()));
//
//        if(descriptions.size() != 0){
//             offers.add(new OffersResponse(offerBidderList.get(0).getDescription_bidder(),descriptions,
//                     offerBidderList.get(0).getState_offer()));
//            offerlist.setListOffers(offers);
//            return offerlist;
//        }

//        return null;
//    }

    public OfferResponseList getOffersBidder(String email) {
        String sql = String.format("SELECT pub.publication_id, o.id_offer,pub.email as email_publisher, o.email_bidder as email_bidder, f1.description as description_publisher, o.state" +
                " FROM public.figure as f1, public.offer as o, public.publication as pub" +
                " WHERE o.email_bidder = '%s' AND o.publication_id = pub.publication_id" +
                " AND pub.number_f = f1.number",email);
        List<OfferResponsePrimary> offerBidderList = jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponsePrimary(
                rs.getString("description_publisher"),
                rs.getInt("id_offer"),
                rs.getString("state"),
                rs.getInt("publication_id")
        ));

        OfferResponseList response = new OfferResponseList();
        List<OffersResponse> listOffers = new ArrayList<>();

        offerBidderList.forEach(e -> listOffers.add(new OffersResponse(e.getDescription_publisher(), this.getDescriptionsBidder(e.getId_offer()), e.getState_offer(),e.getId_offer(),e.getPublication_id()))  );

        response.setListOffers(listOffers);
        return response;

    }

    public OffersFromPublication getOffersFromPublication(String email, String publicationId) {
        String sql = String.format("SELECT pub.publication_id, o.id_offer,pub.email as email_publisher, o.email_bidder as email_bidder, f1.description as description_publisher, f2.description as description_bidder,o.state" +
                " FROM public.publication as pub, public.offer as o, public.figure_user_offer as fuo, public.figure as f1, public.figure as f2, public.user_figure as uf1, public.user_figure as uf2" +
                " WHERE pub.publication_id = %d" +
                " AND pub.publication_id = o.publication_id" +
                " AND o.id_offer = fuo.id_offer" +
                " AND pub.number_f = uf1.number" +
                " AND pub.state_damage = uf1.state_damage" +
                " AND uf1.number = f1.number" +
                " AND fuo.number_f_offer_bidder = uf2.number" +
                " AND fuo.state_damage_f_offer_bidder = uf2.state_damage" +
                " AND uf2.number = f2.number",Integer.parseInt(publicationId));
        List<OfferResponsePrimary> offers = jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponsePrimary(
                rs.getString("description_publisher"),
                rs.getInt("id_offer"),
                rs.getString("state"),
                rs.getInt("publication_id")
        ));

        OffersFromPublication response = new OffersFromPublication();
        List<OffersResponse> listOffers = new ArrayList<>();


        List<Integer> idOffersAlreadyAdded = new ArrayList<>();


        for (OfferResponsePrimary offer: offers) {
            if (!idOffersAlreadyAdded.contains(offer.getId_offer())){
                listOffers.add(new OffersResponse(offer.getDescription_publisher(), this.getDescriptionsBidder(offer.getId_offer()), offer.getState_offer(),offer.getId_offer(), offer.getPublication_id()));
                idOffersAlreadyAdded.add(offer.getId_offer());
            }
        }


        //offers.forEach(e -> (!listOffers.contains(e.getId_oferta()) ? listOffers.add(new OffersResponse(e.getDescription_publisher(), this.getDescriptionsBidder(e.getId_oferta()), e.getState_offer())) : ));
        response.setListOffers(listOffers);

        return response;
    }

    private List<String> getDescriptionsBidder(Integer id_oferta) {
        List<String> descriptionsPublisher = new ArrayList<>();

        String sql2 = String.format("SELECT f.description" +
                " FROM public.figure_user_offer as fuo, public.figure as f" +
                " WHERE id_offer = '%d' AND fuo.number_f_offer_bidder = f.number",id_oferta);
        jdbcTemplate.query(sql2, (rs, rowNum) -> descriptionsPublisher.add(rs.getString("description")));

        return descriptionsPublisher;

    }



    public ResponseCreateOfferDTO createOffer(CreateOfferRequestDTO createOfferRequestDTO) {

        ResponseCreateOfferDTO response = new ResponseCreateOfferDTO();

        //Consultar si la publicacion a la cual se va a ofertar esta activa
        //Esto es para cuando se realiza una contraoferta, tener en cuenta que la publicacion ya no este disponible
        //ya que el boton para contraofertar va a estar en ofertas y no en el muro de publicaciones
        //Si retornamos true es porque creamos la oferta, si retornamos false no se podra contraofertar.

        String sqlIsPublicationActive = String.format("SELECT activated" +
                        " FROM public.publication" +
                        " WHERE publication_id = %d;", createOfferRequestDTO.getId_publication());
        boolean isActive = Boolean.TRUE.equals(jdbcTemplate.queryForObject(sqlIsPublicationActive, (rs, rowNum) -> rs.getBoolean("activated")));

        if (isActive){
            Integer id_offer;
            String sql = String.format("INSERT INTO public.offer(" +
                            " email_bidder, publication_id, state)" +
                            " VALUES ('%s', %d, '%s')" +
                            " RETURNING id_offer;"
                    ,createOfferRequestDTO.getEmail_bidder()
                    ,createOfferRequestDTO.getId_publication()
                    ,createOfferRequestDTO.getState_offer());
            id_offer = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("id_offer"));

            List<FigureOfferDTO> figures = createOfferRequestDTO.getFigures();

            figures.forEach(
                    f -> jdbcTemplate.update(String.format("INSERT INTO public.figure_user_offer(" +
                            " id_offer, number_f_offer_bidder, state_damage_f_offer_bidder, quantity)" +
                            " VALUES ('%d','%s' , '%s', '%d');", id_offer, f.getNumber(), f.getState_damage(), f.getQuantity())));

            response.setMessage("OFERTA CREADA");
            return response;
        }
        response.setMessage(String.format("NO SE PUDO CREAR LA OFERTA PORQUE LA PUBLICACION '%s' NO ESTA MAS ACTIVA", createOfferRequestDTO.getId_publication()));
        return response;
    }



    public boolean acceptOffer(String id) {

            // Setear de estado de la oferta a ACEPTADA
        String sqlOffer = String.format("UPDATE public.offer as o" +
                " SET acepted_date=NOW(), state='ACEPTADA'" +
                " WHERE o.id_offer = %d;",Integer.parseInt(id));
        jdbcTemplate.update(sqlOffer);

            // Buscar el ID de la publicacion relacionada con la oferta
        String sqlPubIdFromOfferId = String.format("SELECT publication_id FROM public.offer WHERE id_offer = %d;",Integer.parseInt(id));
        Integer id_pub = jdbcTemplate.queryForObject(sqlPubIdFromOfferId, (rs, rowNum) -> rs.getInt("publication_id"));

            // Cancelar todas las demas ofertas asociadas a la publicacion
            // No se rechazan porque da lugar a que se pueda contraofertar
        String sqlCancelOffers = String.format("UPDATE public.offer as o" +
                " SET state='CANCELADA'" +
                " WHERE o.publication_id = %d AND o.id_offer <> %d;", id_pub, Integer.parseInt(id));
        jdbcTemplate.update(sqlCancelOffers);

            // Desactivar la publicacion
        String sqlPublication = String.format("UPDATE public.publication as pub" +
                " SET pending_exchange='DESACTIVO', activated=false" +
                " WHERE pub.publication_id=%d;",id_pub);
        jdbcTemplate.update(sqlPublication);

            // Buscar el quantity de la figurita asociada a la publicacion
        String sqlQuantityPublication = String.format("SELECT pub.email, uf.quantity, uf.number, uf.state_damage" +
                " FROM public.user_figure as uf, public.publication as pub" +
                " WHERE pub.publication_id = %d AND pub.state_damage = uf.state_damage AND pub.number_f = uf.number AND pub.email = uf.email", id_pub);
        //Integer quantity = jdbcTemplate.queryForObject(sqlQuantityPublication, (rs, rowNum) -> rs.getInt("quantity"));
        UserFiguresResponseDTO userFigure = jdbcTemplate.queryForObject(sqlQuantityPublication, (rs, rowNum) -> new UserFiguresResponseDTO(
                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getInt("quantity"),
                        ""));

            // Si el quantity es 1, elimino la tupla, sino resto en 1 la cantidad.
            // Suponemos que una figurita publicada no puede al mismo tiempo usarse para ofertar
        if (userFigure.getQuantity() > 1) {
            String sqlLessQuantity = String.format("UPDATE public.user_figure" +
                    " SET quantity = uf.quantity - 1" +
                    " FROM public.user_figure as uf, public.publication as pub" +
                    " WHERE pub.publication_id = %d AND pub.state_damage = uf.state_damage AND pub.number_f = uf.number", id_pub);
            jdbcTemplate.update(sqlLessQuantity);
        }else {
                // Buscar todos los ids usados por el publicante en ofertas
            String sqlOffersToDelete = String.format("SELECT o.id_offer" +
                    " FROM public.offer as o, public.figure_user_offer as fuo" +
                    " WHERE o.email_bidder = '%s'" +
                    " AND fuo.number_f_offer_bidder= '%s'" +
                    " AND fuo.state_damage_f_offer_bidder= '%s'" +
                    " AND o.id_offer=fuo.id_offer", userFigure.getEmail(), userFigure.getNumber(), userFigure.getState_damage());

            List<Integer> offersToDelete = jdbcTemplate.query(sqlOffersToDelete, (rs, rowNum) ->
                    rs.getInt("id_offer"));

            offersToDelete.forEach(offer -> jdbcTemplate.update(String.format("UPDATE public.offer" +
                    " SET state='CANCELADA'" +
                    " WHERE id_offer=%d;", offer)));

            String sqlDeleteUserFigures = String.format("DELETE" +
                    " FROM public.user_figure as uf" +
                    " WHERE uf.email= '%s'" +
                    " AND uf.number= '%s'" +
                    " AND uf.state_damage ='%s'", userFigure.getEmail(), userFigure.getNumber(), userFigure.getState_damage());
            jdbcTemplate.update(sqlDeleteUserFigures);
        }

        //Nos posicionamos como ofertante y debemos quitarle la figurita utilizada en la oferta

            // Obtener todas las figuritas de la oferta
        String sqlAllFiguresinOffer = String.format("SELECT fuo.number_f_offer_bidder as number, fuo.state_damage_f_offer_bidder as state_damage" +
                " FROM public.offer as o" +
                " JOIN public.figure_user_offer as fuo" +
                " ON o.id_offer = %d AND o.id_offer = fuo.id_offer", Integer.parseInt(id));
        List<FigureOfferDTO> allFiguresinOffer = jdbcTemplate.query(sqlAllFiguresinOffer, (rs,rowNum) ->
                new FigureOfferDTO(rs.getString("number"), rs.getString("state_damage"),0));

            // Obtener el email del ofertante
        String emailBidder = jdbcTemplate.queryForObject(String.format("SELECT o.email_bidder FROM public.offer as o WHERE o.id_offer = %d",id), (rs,rowNum) -> rs.getString("email_bidder"));

        if(!allFiguresinOffer.isEmpty()){
            for (FigureOfferDTO figure: allFiguresinOffer) {
                String sqlGetFigureQuantity = String.format("SELECT quantity" +
                        " FROM public.user_figure" +
                        " WHERE email = '%s' AND number = '%s' AND state_damage = '%s';", emailBidder,figure.getNumber(),figure.getState_damage());

                Integer quantity = jdbcTemplate.queryForObject(sqlGetFigureQuantity, (rs, rowNum) -> rs.getInt("quantity"));

                //continua con si es > 1 se le resta 1 a la cantidad de la figurita
                //si es 1 se elimina la figurita del usuario y ademas se busca q la figurita no haya sido usada
                //en publicaciones u ofertas
                if (quantity > 1) {
                    String sqlLessQuantity = String.format("UPDATE public.user_figure as uf" +
                            " SET quantity = uf.quantity - 1" +
                            " WHERE uf.email = '%s' AND uf.number = '%s' AND uf.state_damage = '%s'", emailBidder, figure.getNumber(), figure.getState_damage());
                    jdbcTemplate.update(sqlLessQuantity);
                }else {
                    // Buscar todos los ids usados por el ofertante en ofertas
                    String sqlOffersToDelete = String.format("SELECT o.id_offer" +
                            " FROM public.offer as o, public.figure_user_offer as fuo" +
                            " WHERE o.email_bidder = '%s'" +
                            " AND fuo.number_f_offer_bidder= '%s'" +
                            " AND fuo.state_damage_f_offer_bidder= '%s'" +
                            " AND o.id_offer=fuo.id_offer", emailBidder, figure.getNumber(), figure.getState_damage());

                    List<Integer> offersToDelete = jdbcTemplate.query(sqlOffersToDelete, (rs, rowNum) ->
                            rs.getInt("id_offer"));

                    offersToDelete.forEach(offer -> jdbcTemplate.update(String.format("UPDATE public.offer" +
                            " SET state='CANCELADA'" +
                            " WHERE id_offer=%d;", offer)));

                    String sqlDeleteUserFigures = String.format("DELETE" +
                            " FROM public.user_figure as uf" +
                            " WHERE uf.email= '%s'" +
                            " AND uf.number= '%s'" +
                            " AND uf.state_damage ='%s'", emailBidder, figure.getNumber(), figure.getState_damage());
                    jdbcTemplate.update(sqlDeleteUserFigures);
                }
            }
        }
        return true;
    }

    public boolean rejectOffer(String id){
        String sqlReject = String.format("UPDATE public.offer as o SET state = 'RECHAZADA' " +
                "WHERE o.id_offer = %d", Integer.parseInt(id));

        jdbcTemplate.update(sqlReject);

        return true;
    }


//    public Offer updateOffer(Offer offer){
//        Offer existingOffer = offerRepository.findById(offer.getIdOffer()).orElse(null);
//        existingOffer.setAcepted_date(offer.getAcepted_date());
//        existingOffer.setState(offer.getState());
//
//        return offerRepository.save(existingOffer);
//    }
}
