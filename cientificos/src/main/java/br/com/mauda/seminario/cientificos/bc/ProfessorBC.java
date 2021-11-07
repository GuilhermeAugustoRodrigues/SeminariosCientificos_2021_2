package br.com.mauda.seminario.cientificos.bc;

import br.com.mauda.seminario.cientificos.dao.ProfessorDAO;
import br.com.mauda.seminario.cientificos.dto.ProfessorDTO;
import br.com.mauda.seminario.cientificos.model.Professor;

<<<<<<< HEAD
public class ProfessorBC extends PatternCrudBC<Professor, ProfessorDTO, ProfessorDAO> {
=======
public class ProfessorBC extends PatternCrudBC<Professor, ProfessorDAO> {
>>>>>>> 5df95157ec70f83f255ba27b478038cfd3e0e7fe
    public static ProfessorBC getInstance() {
        return instance;
    }

    private static final ProfessorBC instance = new ProfessorBC();

    private ProfessorBC() {
        this.dao = ProfessorDAO.getInstance();
    }
}
