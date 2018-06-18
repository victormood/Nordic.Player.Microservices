package io.pivotal.microservices.accounts;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.mwt.movies.controller.MoviesController;
import com.mwt.movies.ds.MoviesRepository;
import com.mwt.movies.ds.entities.Movie;

public class AccountsControllerTests extends AbstractAccountControllerTests {

	protected static final Movie theAccount = new Movie(ACCOUNT_1, ACCOUNT_1_NAME);

	protected static class TestAccountRepository implements MoviesRepository {

		@Override
		public Movie findByNumber(String accountNumber) {
			if (accountNumber.equals(ACCOUNT_1))
				return theAccount;
			else
				return null;
		}

		@Override
		public List<Movie> findByOwnerContainingIgnoreCase(String partialName) {
			List<Movie> movies = new ArrayList<Movie>();

			if (ACCOUNT_1_NAME.toLowerCase().indexOf(partialName.toLowerCase()) != -1)
				movies.add(theAccount);

			return movies;
		}

		@Override
		public int countAccounts() {
			return 1;
		}
	}

	protected TestAccountRepository testRepo = new TestAccountRepository();

	@Before
	public void setup() {
		accountController = new MoviesController(testRepo);
	}
}
